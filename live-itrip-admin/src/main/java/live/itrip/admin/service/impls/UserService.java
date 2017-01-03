package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.WebLoginData;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.UserExpandMapper;
import live.itrip.admin.dao.UserMapper;
import live.itrip.admin.dao.UserOnlineMapper;
import live.itrip.admin.email.EmailHandler;
import live.itrip.admin.model.AdminModule;
import live.itrip.admin.model.AdminUser;
import live.itrip.admin.model.User;
import live.itrip.admin.model.UserExpand;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminModuleService;
import live.itrip.admin.service.intefaces.IUserService;
import live.itrip.admin.shiro.BaseResultAuthenticationException;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.common.security.Md5Utils;
import live.itrip.common.util.CookieUtils;
import live.itrip.common.util.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Feng on 2016/10/12.
 */
@Service
public class UserService extends BaseService implements IUserService {

    @Autowired
    private IAdminModuleService iAdminModuleService;
    @Autowired
    private UserOnlineMapper userOnlineMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserExpandMapper userExpandMapper;

    @Override
    public AdminUser getCurrentLoginUser() {
        try {
            Subject currentSubject = SecurityUtils.getSubject();
            currentSubject.isPermitted();
            AdminUser user = (AdminUser) currentSubject.getPrincipal();
            return user;
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * 用户登录
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void userLogin(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        WebLoginData loginData = JSON.parseObject(decodeJson, WebLoginData.class);
        BaseResult result = new BaseResult();

        if (loginData == null) {
            this.paramInvalid(response, "Login Datas");
            return;
        }

        if (StringUtils.isEmpty(loginData.getUserName()) || StringUtils.isEmpty(loginData.getPwd())) {
            this.paramInvalid(response, "Email or Password");
            return;
        }
//        if (StringUtils.isEmpty(loginData.getCaptcha())) {
//            this.paramInvalid(response, "Captcha");
//            return;
//        }

        Subject currentSubject = SecurityUtils.getSubject();
        if (currentSubject.isAuthenticated()) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
            return;
        }

        try {
            // 身份验证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginData.getUserName(), loginData.getPwd());
            // 记住我
            boolean rememberMe = true;
            if (rememberMe) {
                usernamePasswordToken.setRememberMe(rememberMe);
            }
            currentSubject.login(usernamePasswordToken);

            // 用户信息正确,通过验证
            AdminUser user = (AdminUser) currentSubject.getPrincipal();
            // 写入session
            currentSubject.getSession().setAttribute(Constants.SESSION_USER, user);
            // 写入 cookie
            CookieUtils.setCookie(request, response, Constants.COOKIE_TOKEN_NAME, user.getToken());

            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
            return;
        } catch (BaseResultAuthenticationException e) {
            // 身份验证失败
            result = e.getResult();
            // logout
            currentSubject.logout();
        }

        this.writeResponse(response, result);
    }

    @Override
    public void selectModulesByUser(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();

        try {
            Subject currentSubject = SecurityUtils.getSubject();
            currentSubject.isPermitted();
//            AdminUser user = (AdminUser) currentSubject.getPrincipal();

            List<AdminModule> list = iAdminModuleService.selectAllModules();
            if (list != null) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                result.setData(list);
                this.writeResponse(response, result);
                return;
            }
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void userLogout(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws IOException {
        BaseResult result = new BaseResult();

        try {
            Subject currentSubject = SecurityUtils.getSubject();
            AdminUser user = (AdminUser) currentSubject.getPrincipal();

            // logout
            currentSubject.logout();

            // remove from online table
            this.userOnlineMapper.deleteByUserIdOrUserEmail(user.getId(), user.getEmail());

            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
        } catch (Exception ex) {
            this.writeResponse(response, result);
        }
    }

    /**
     * 注册
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void register(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult userRegisterResponse = new BaseResult();
        User user = JSON.parseObject(decodeJson, User.class);
        if (user != null) {
            String oldPwd = user.getPassword();

            User local = userMapper.selectByUserName(user.getEmail());
            if (local == null) {

                String salt = UuidUtils.getUuidLowerCase(false);
                String pwd = Md5Utils.getStringMD5(salt + user.getPassword());
                user.setPassword(pwd);
                user.setSalt(salt);
                user.setStatus(User.STATUS_NORMAL);
                user.setCreateTime(System.currentTimeMillis());

                int ret = userMapper.insertSelective(user);
                if (ret > 0) {
                    userRegisterResponse.setCode(ErrorCode.SUCCESS.getCode());

                    // send email
                    EmailHandler.getInstance().put(new EmailHandler.EmailEntity());

                    // do login
                    Subject currentSubject = SecurityUtils.getSubject();
                    // 身份验证
                    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getEmail(), oldPwd);
                    // 记住我
                    boolean rememberMe = true;
                    if (rememberMe) {
                        usernamePasswordToken.setRememberMe(rememberMe);
                    }
                    currentSubject.login(usernamePasswordToken);

                    // 写入session
                    currentSubject.getSession().setAttribute(Constants.SESSION_USER, user);
                    // 写入 cookie
                    CookieUtils.setCookie(request, response, Constants.COOKIE_TOKEN_NAME, user.getToken());
                } else {
                    userRegisterResponse.setCode(ErrorCode.UNKNOWN.getCode());
                }
            } else {
                userRegisterResponse.setCode(ErrorCode.USER_AUTH_EXIST.getCode());
                userRegisterResponse.setMsg(ErrorCode.USER_AUTH_EXIST.getMessage());
            }
        }

        this.writeResponse(response, userRegisterResponse);
    }

    /**
     * 修改密码
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void updatePassword(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        BaseResult userUpdatePwdResponse = new BaseResult();
        Long uid = jsonObject.getLong("uid");
        String originPwd = jsonObject.getString("originPwd");
        String password = jsonObject.getString("password");

        if (uid == null) {
            this.paramInvalid(response, "uid");
            return;
        }

        if (StringUtils.isEmpty(originPwd)) {
            this.paramInvalid(response, "originPwd");
            return;
        }

        if (StringUtils.isEmpty(password)) {
            this.paramInvalid(response, "password");
            return;
        }

        User user = this.userMapper.selectByPrimaryKey(uid);
        if (user == null) {
            userUpdatePwdResponse.setCode(ErrorCode.USER_AUTH_INVALID.getCode());
            userUpdatePwdResponse.setMsg(String.format("参数uid(%s)无效,无此用户。", uid));
            this.writeResponse(response, userUpdatePwdResponse);
            return;
        }

        if (Md5Utils.getStringMD5(user.getSalt() + originPwd).equals(user.getPassword())) {
            // 原密码正确
            String pwd = Md5Utils.getStringMD5(user.getSalt() + password);
            int ret = this.userMapper.updatePasswordById(user.getId(), pwd);
            if (ret > 0) {
                // success
                userUpdatePwdResponse.setCode(ErrorCode.SUCCESS.getCode());
            } else {
                // failed
                userUpdatePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
            }
        } else {
            // 原密码错误
            userUpdatePwdResponse.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
            userUpdatePwdResponse.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
        }
        this.writeResponse(response, userUpdatePwdResponse);

    }

    /**
     * 修改用户信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void updateUserInfo(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        User user = JSON.parseObject(decodeJson, User.class);
        BaseResult userUpdateResponse = new BaseResult();

        if (user == null) {
            userUpdateResponse.setCode(ErrorCode.PARAM_INVALID.getCode());
            userUpdateResponse.setMsg("参数user(null)无效。");
            this.writeResponse(response, userUpdateResponse);
            return;
        }
        if (user.getId() == null) {
            userUpdateResponse.setCode(ErrorCode.PARAM_INVALID.getCode());
            userUpdateResponse.setMsg(String.format("参数user id(%s)无效。", user.getId()));
            this.writeResponse(response, userUpdateResponse);
            return;
        }

        user.setPassword(null); // 不修改密码
        user.setSalt(null);

        int ret = this.userMapper.updateByPrimaryKeySelective(user);
        if (ret > 0) {
            // success
            userUpdateResponse.setCode(ErrorCode.SUCCESS.getCode());
        } else {
            // failed
            userUpdateResponse.setCode(ErrorCode.UNKNOWN.getCode());
        }

        this.writeResponse(response, userUpdateResponse);

    }

    /**
     * 找回密码
     *
     * @param response
     * @param request
     */
    @Override
    public void retrievePassword(String decodeJson, HttpServletResponse response,
                                 HttpServletRequest request) {
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        BaseResult retrievePwdResponse = new BaseResult();

        String email = jsonObject.getString("email");
        // 发送邮件
        if (!StringUtils.isEmpty(email)) {
            User user = this.userMapper.selectByUserName(email);
            String initpwd = UuidUtils.getUuidLowerCase(false).substring(0, 6);
            String pwd = Md5Utils.getStringMD5(user.getSalt() + initpwd);

            int ret = this.userMapper.updatePasswordById(user.getId(), pwd);
            if (ret > 0) {
                // success
                retrievePwdResponse.setCode(ErrorCode.SUCCESS.getCode());
                // TODO send email

            } else {
                // failed
                retrievePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
            }

            this.writeResponse(response, retrievePwdResponse);
            return;
        }

        // 发送短信
//        if (!StringUtils.isEmpty(retrievePwdRequest.getMobile())) {
//            User user = this.userMapper.selectByUserName(email);
//            String initpwd = UuidUtils.getUuidLowerCase(false).substring(0, 6);
//            String pwd = Md5Utils.getStringMD5(user.getSalt() + initpwd);
//
//            int ret = this.userMapper.updatePasswordById(user.getId(), pwd);
//            if (ret > 0) {
//                // success
//                retrievePwdResponse.setCode(ErrorCode.SUCCESS.getCode());
//                // TODO send sms
//
//            } else {
//                // failed
//                retrievePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
//            }
//
//            this.writeResponse(response, retrievePwdResponse);
//            return;
//        }

        retrievePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
        this.writeResponse(response, retrievePwdResponse);

    }

    @Override
    public UserExpand selectUserExpand(Long uid) {
        return userExpandMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateUserExpand(UserExpand userExpand) {
        if (userExpandMapper.selectByPrimaryKey(userExpand.getId()) == null) {
            return userExpandMapper.insertSelective(userExpand);
        }
        return userExpandMapper.updateByPrimaryKeySelective(userExpand);
    }

    @Override
    public void refreshCache(AdminUser currentUser) {
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute(Constants.SESSION_USER, currentUser);
    }


}
