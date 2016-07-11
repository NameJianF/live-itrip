package live.itrip.sso.service.impls;

import com.alibaba.fastjson.JSON;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import live.itrip.common.util.Md5Utils;
import live.itrip.common.util.UuidUtils;
import live.itrip.sso.bean.AuthUserRequest;
import live.itrip.sso.bean.LoginRequest;
import live.itrip.sso.bean.LogoutRequest;
import live.itrip.sso.common.Config;
import live.itrip.sso.common.Constants;
import live.itrip.sso.common.exception.ApiException;
import live.itrip.sso.dao.UserMapper;
import live.itrip.sso.dao.UserOnlineMapper;
import live.itrip.sso.dao.UserTokenMapper;
import live.itrip.sso.model.User;
import live.itrip.sso.model.UserOnline;
import live.itrip.sso.model.UserToken;
import live.itrip.sso.service.BaseService;
import live.itrip.sso.service.interfaces.ISsoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/3/8.
 */
@Service("ssoService")
public class SsoService extends BaseService implements ISsoService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private UserOnlineMapper userOnlineMapper;

    /**
     * 登录
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void login(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws ApiException {
        try {
            LoginRequest loginRequest = JSON.parseObject(decodeJson, LoginRequest.class);
            BaseResult result = new BaseResult();
            result.setOp(loginRequest.getOp());

            if (Config.NORMAL.equals(loginRequest.getData().getSource())) {
                // 正常登录： email/mobile/username
                User user = this.userMapper.selectByUserName(loginRequest.getData().getEmail());
                if (user == null) {
                    // 该用户不存在
                    result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
                    result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
                } else {
                    if (user.getStatus() == null || user.getStatus().equals("0")) {
                        // 新用户，未验证
                        result.setCode(ErrorCode.USER_AUTH_INVALID.getCode());
                        result.setMsg(ErrorCode.USER_AUTH_INVALID.getMessage());
                    } else if (user.getStatus().equals("1")) {
                        // 正常用户
                        // 验证密码
                        String password = Md5Utils.getStringMD5(user.getSalt() + loginRequest.getData().getPassword());

                        if (password.equals(user.getPassword())) {
                            // 生成并保存token
                            UserToken token = generateUserToken(user, loginRequest, request);

                            // insert into online table
                            insertIntoOnlineTable(user);

                            // 密码正确，保存 session信息
                            request.getSession().setAttribute(Constants.SESSION_USER, user);
                            result.setCode(ErrorCode.SUCCESS.getCode());

                            // 处理用户返回信息
                            user.setPassword(null);
                            user.setSalt(null);
                            user.setUpdateTime(null);
                            user.setToken(token.getAuthToken());

                            // 设置用户信息
                            result.setData(user);
                        } else {
                            // 密码错误
                            result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
                            result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
                        }

                        result.setCode(ErrorCode.UNKNOWN.getCode());

                    } else if (user.getStatus().equals("2")) {
                        // 该用户无效
                        result.setCode(ErrorCode.USER_INVALID.getCode());
                        result.setMsg(ErrorCode.USER_INVALID.getMessage());
                    }
                }
            } else if (Config.WEICHAT.equals(loginRequest.getData().getSource())) {
                // 微信
            }

            this.writeResponse(response, result);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), e, true);
        }
    }

    /**
     * 用户退出
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void logout(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws ApiException {
        try {
            LogoutRequest logoutRequest = JSON.parseObject(decodeJson, LogoutRequest.class);
            BaseResult result = new BaseResult();
            result.setOp(logoutRequest.getOp());

            // remove from online table
            int ret = this.userOnlineMapper.deleteByUserIdOrUserEmail(logoutRequest.getUid(), logoutRequest.getEmail());

            if (ret <= 0) {
                result.setCode(ErrorCode.UNKNOWN.getCode());
            }
            this.writeResponse(response, result);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), e, true);
        }
    }

    /**
     * 用户鉴权,是否是合法用户
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void authUser(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws ApiException {
        try {
            AuthUserRequest authUserRequest = JSON.parseObject(decodeJson, AuthUserRequest.class);
            BaseResult authUserResponse = new BaseResult();
            authUserResponse.setOp(authUserRequest.getOp());

            User user = null;
            if (!StringUtils.isEmpty(authUserRequest.getEmail())) {
                user = this.userMapper.selectByUserName(authUserRequest.getEmail());
            } else if (!StringUtils.isEmpty(authUserRequest.getMobile())) {
                user = this.userMapper.selectByUserName(authUserRequest.getEmail());
            }

            if (user == null) {
                authUserResponse.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
            } else {
                if (User.STATUS_INIT.equalsIgnoreCase(user.getStatus()) ||
                        User.STATUS_NORMAL.equalsIgnoreCase(user.getStatus())) {
                    // 正常
                    authUserResponse.setCode(ErrorCode.SUCCESS.getCode());
                } else {
                    // 不可用
                    authUserResponse.setCode(ErrorCode.USER_AUTH_INVALID.getCode());
                }
            }

            this.writeResponse(response, authUserResponse);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), e, true);
        }
    }

    /**
     * 生成登录token
     *
     * @return
     */

    private UserToken generateUserToken(User user, LoginRequest loginRequest, HttpServletRequest request) {
        UserToken token = new UserToken();
        token.setAuthToken(UuidUtils.getUuidLowerCase(false));
        token.setUserEmail(user.getEmail());
        token.setUserId(user.getId());
        token.setApiKey(loginRequest.getApikey());
        token.setSource(loginRequest.getData().getSource());
        token.setDomain(request.getRemoteHost());
        token.setClientVersion(loginRequest.getData().getClientVersion());
        long time = System.currentTimeMillis();
        token.setExpireTime(time + Constants.TOKEN_EXPIRE_TIME);  //
        token.setCreateTime(time);

        // insert to db
        int ret = userTokenMapper.insertSelective(token);

        return token;
    }

    /**
     * 添加到在线表
     */
    private boolean insertIntoOnlineTable(User user) {
        UserOnline record = new UserOnline();
        record.setUserId(user.getId());
        record.setUserEmail(user.getEmail());
        record.setUserName(user.getUserName());
        record.setUserSource(user.getSource());
        record.setUserSubsource(user.getSubsource());
//        record.setCreateTime(System.currentTimeMillis()); // 采用数据库默认值
        int ret = userOnlineMapper.insertSelective(record);
        if (ret > 0) {
            // success
            return true;
        } else {
            // falied
            return false;
        }
    }
}
