package live.itrip.sso.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import live.itrip.common.util.Md5Utils;
import live.itrip.common.util.UuidUtils;
import live.itrip.sso.bean.RetrievePwdRequest;
import live.itrip.sso.bean.UserRegisterRequest;
import live.itrip.sso.bean.UserUpdatePwdRequest;
import live.itrip.sso.bean.UserUpdateRequest;
import live.itrip.sso.common.exception.ApiException;
import live.itrip.sso.dao.UserMapper;
import live.itrip.sso.model.User;
import live.itrip.sso.service.BaseService;
import live.itrip.sso.service.interfaces.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JianF on 2015/12/22.
 */
@Service
public class UserService extends BaseService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void test() {
        User user = userMapper.selectByUserName("admin");
        System.err.println(user.toString());
    }


    /**
     * 注册
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void register(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws ApiException {
        try {
            UserRegisterRequest userRegisterRequest = JSON.parseObject(decodeJson, UserRegisterRequest.class);
            BaseResult userRegisterResponse = new BaseResult();
            userRegisterResponse.setOp(userRegisterRequest.getOp());

            User user = userRegisterRequest.getData();
            if (user != null) {
                String salt = UuidUtils.getUuidLowerCase(false);
                String pwd = Md5Utils.getStringMD5(salt + user.getPassword());
                user.setPassword(pwd);
                user.setSalt(salt);
                user.setCreateTime(System.currentTimeMillis());

                int userid = userMapper.insertSelective(userRegisterRequest.getData());
                if (userid > 0) {
                    userRegisterResponse.setCode(ErrorCode.SUCCESS.getCode());

                    JSONObject data = new JSONObject();
                    data.put("uid", userid);
                    userRegisterResponse.setData(data);
                } else {
                    userRegisterResponse.setCode(ErrorCode.UNKNOWN.getCode());
                }
            }

            this.writeResponse(response, userRegisterResponse);
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), ex, true);
        }
    }

    /**
     * 修改密码
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void updatePassword(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws ApiException {
        try {
            UserUpdatePwdRequest userUpdatePwdRequest = JSON.parseObject(decodeJson, UserUpdatePwdRequest.class);
            BaseResult userUpdatePwdResponse = new BaseResult();
            userUpdatePwdResponse.setOp(userUpdatePwdRequest.getOp());

            if (userUpdatePwdRequest.getUid() == null) {
                this.paramInvalid(response, "uid", userUpdatePwdRequest.getUid().toString());
                return;
            }

            if (StringUtils.isEmpty(userUpdatePwdRequest.getOriginPwd())) {
                this.paramInvalid(response, "originPwd", userUpdatePwdRequest.getOriginPwd());
                return;
            }

            if (StringUtils.isEmpty(userUpdatePwdRequest.getPassword())) {
                this.paramInvalid(response, "password", userUpdatePwdRequest.getPassword());
                return;
            }

            User user = this.userMapper.selectByPrimaryKey(userUpdatePwdRequest.getUid());
            if (user == null) {
                userUpdatePwdResponse.setCode(ErrorCode.USER_AUTH_INVALID.getCode());
                userUpdatePwdResponse.setMsg(String.format("参数uid(%s)无效,无此用户。", userUpdatePwdRequest.getUid()));
                this.writeResponse(response, userUpdatePwdResponse);
                return;
            }

            if (Md5Utils.getStringMD5(user.getSalt() + userUpdatePwdRequest.getOriginPwd()).equals(user.getPassword())) {
                // 原密码正确
                String pwd = Md5Utils.getStringMD5(user.getSalt() + userUpdatePwdRequest.getPassword());
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
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), ex, true);
        }
    }

    /**
     * 修改用户信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void updateUserInfo(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws ApiException {
        try {
            UserUpdateRequest userUpdateRequest = JSON.parseObject(decodeJson, UserUpdateRequest.class);
            BaseResult userUpdateResponse = new BaseResult();
            userUpdateResponse.setOp(userUpdateRequest.getOp());

            User user = userUpdateRequest.getData();
            if (user == null) {
                userUpdateResponse.setCode(ErrorCode.PARAM_INVALID.getCode());
                userUpdateResponse.setMsg("参数user(null)无效。");
                this.writeResponse(response, userUpdateResponse);
                return;
            }
            if (userUpdateRequest.getData().getId() == null) {
                userUpdateResponse.setCode(ErrorCode.PARAM_INVALID.getCode());
                userUpdateResponse.setMsg(String.format("参数user id(%s)无效。", userUpdateRequest.getData().getId()));
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
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), ex, true);
        }
    }

    /**
     * 找回密码
     *
     * @param response
     * @param request
     */
    @Override
    public void retrievePassword(String decodeJson, HttpServletResponse response,
                                 HttpServletRequest request) throws ApiException {
        try {
            RetrievePwdRequest retrievePwdRequest = JSON.parseObject(decodeJson, RetrievePwdRequest.class);
            BaseResult retrievePwdResponse = new BaseResult();
            retrievePwdResponse.setOp(retrievePwdRequest.getOp());

            // 发送邮件
            if (!StringUtils.isEmpty(retrievePwdRequest.getEmail())) {
                User user = this.userMapper.selectByUserName(retrievePwdRequest.getEmail());
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
            if (!StringUtils.isEmpty(retrievePwdRequest.getMobile())) {
                User user = this.userMapper.selectByUserName(retrievePwdRequest.getEmail());
                String initpwd = UuidUtils.getUuidLowerCase(false).substring(0, 6);
                String pwd = Md5Utils.getStringMD5(user.getSalt() + initpwd);

                int ret = this.userMapper.updatePasswordById(user.getId(), pwd);
                if (ret > 0) {
                    // success
                    retrievePwdResponse.setCode(ErrorCode.SUCCESS.getCode());
                    // TODO send sms

                } else {
                    // failed
                    retrievePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
                }

                this.writeResponse(response, retrievePwdResponse);
                return;
            }

            retrievePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
            this.writeResponse(response, retrievePwdResponse);
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), ex, true);
        }
    }
}
