package live.itrip.web.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.common.util.CookieUtils;
import live.itrip.web.bean.BootStrapDataTableList;
import live.itrip.web.bean.PagerInfo;
import live.itrip.web.bean.WebLoginData;
import live.itrip.web.common.Constants;
import live.itrip.web.dao.AdminUserMapper;
import live.itrip.web.model.AdminModule;
import live.itrip.web.model.AdminUser;
import live.itrip.web.model.AdminUserPermission;
import live.itrip.web.service.BaseService;
import live.itrip.web.service.intefaces.IAdminModuleService;
import live.itrip.web.service.intefaces.IAdminUserPermissionService;
import live.itrip.web.service.intefaces.IAdminUserService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.common.security.Md5Utils;
import live.itrip.common.util.UuidUtils;
import live.itrip.web.shiro.BaseResultAuthenticationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feng on 2016/10/24.
 */
@Service
public class AdminUserService extends BaseService implements IAdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private IAdminModuleService iAdminModuleService;
    @Autowired
    private IAdminUserPermissionService iAdminUserPermissionService;

    @Override
    public AdminUser selectAdminUserById(Long userId) {
        return adminUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public AdminUser saveUserInfo(AdminUser user) {
        AdminUser record = new AdminUser();
        record.setId(user.getId());
        record.setUserName(user.getUserName());
        record.setEmail(user.getEmail());
        record.setMobile(user.getMobile());
        record.setLevel(user.getLevel());
        record.setStatus(user.getStatus());
        record.setIdentity(user.getIdentity());
        record.setCreateTime(System.currentTimeMillis());
        Integer ret = adminUserMapper.insert(record);
        if (ret > 0) {
            return record;
        }
        return null;
    }

    @Override
    public void selectAdminUsers(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<AdminUser> result = new BootStrapDataTableList<>();
        try {
            // 解析查询条件
            JSONArray jsonarray = JSONArray.parseArray(decodeJson);
            String queryUserName = null;
            Integer queryDepart = null;
            Integer queryGroup = null;
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
                if (obj.get("name").equals("queryUserName")) {
                    queryUserName = obj.get("value").toString();
                } else if (obj.get("name").equals("queryDepart")) {
                    queryDepart = obj.getInteger("value");
                } else if (obj.get("name").equals("queryGroup")) {
                    queryGroup = obj.getInteger("value");
                }
            }


            PagerInfo pagerInfo = this.getPagerInfo(jsonarray);
            Integer count = adminUserMapper.countAll();
            if (StringUtils.isNotEmpty(queryUserName)) {
                queryUserName = "'%" + queryUserName.trim() + "%'";
            }
            List<AdminUser> userList = adminUserMapper.selectAdminUsers(queryDepart, queryGroup, queryUserName, pagerInfo.getStart(), pagerInfo.getLength());

            if (userList != null) {
                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(count);
                result.setiTotalDisplayRecords(count);
                result.setAaData(userList);

                // response
                this.writeResponse(response, result);
                return;
            }
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }

        BaseResult error = new BaseResult();
        error.setCode(ErrorCode.UNKNOWN.getCode());
        this.writeResponse(response, error);
    }

    @Override
    public void selectAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Long memberId = Long.valueOf(jsonObject.get("memberId").toString());
        if (memberId != null) {
            AdminUser AdminUser = this.adminUserMapper.selectByPrimaryKey(memberId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(AdminUser);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Long memberId = Long.valueOf(jsonObject.get("memberId").toString());
        if (memberId != null) {
            Integer ret = this.adminUserMapper.deleteByPrimaryKey(memberId);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void editAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        AdminUser adminUser = JSON.parseObject(decodeJson, AdminUser.class);
        Integer ret = this.editAdminUser(adminUser);
        if (ret > 0) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public int editAdminUser(AdminUser adminUser) {
        Integer ret;
        if (adminUser.getId() == null) {
            // new
            adminUser.setCreateTime(System.currentTimeMillis());
            ret = this.adminUserMapper.insertSelective(adminUser);
        } else {
            // update
            ret = this.adminUserMapper.updateByPrimaryKeySelective(adminUser);
        }
        return ret;
    }

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
//            boolean rememberMe = true;
//            if (rememberMe) {
//                usernamePasswordToken.setRememberMe(rememberMe);
//            }
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
    public void userLogout(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();

        try {
            Subject currentSubject = SecurityUtils.getSubject();
            AdminUser user = (AdminUser) currentSubject.getPrincipal();

            // logout
            currentSubject.logout();

            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
        } catch (Exception ex) {
            this.writeResponse(response, result);
        }
    }

    @Override
    public void selectModulesByUser(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();

        try {
            Subject currentSubject = SecurityUtils.getSubject();
            List<AdminModule> list = iAdminModuleService.selectAllModules();
            if (list != null) {
//                List<AdminModule> tmp = new ArrayList<>();
//                for (AdminModule module : list) {
//                    String per = String.format("%s:%s", module.getId(), Constants.Operation.VIEW);
//                    if (currentSubject.isPermitted(per)) {
//                        tmp.add(module);
//                    }
//                }

                result.setCode(ErrorCode.SUCCESS.getCode());
//                result.setData(tmp);
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
    public void updatePassword(String decodeJson, HttpServletResponse response, HttpServletRequest request) {

    }

    @Override
    public void register(String decodeJson, HttpServletResponse response, HttpServletRequest request) {

    }

}
