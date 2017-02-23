package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.api.sso.SsoApi;
import live.itrip.admin.api.sso.bean.LoginResponse;
import live.itrip.admin.api.sso.bean.User;
import live.itrip.admin.bean.WebLoginData;
import live.itrip.admin.common.Constants;
import live.itrip.admin.email.EmailHandler;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IUserService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.common.security.Md5Utils;
import live.itrip.common.util.CookieUtils;
import live.itrip.common.util.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Feng on 2016/10/12.
 */
@Service
public class UserService extends BaseService implements IUserService {

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

        // sso 获取用户信息

        SsoApi ssoApi = new SsoApi();
        LoginResponse loginResponse = ssoApi.userLogin(loginData.getUserName(), loginData.getPwd());
        if (loginResponse.getCode() != null && loginResponse.getCode() == 0) {
            // 校验账号密码
            User user = loginResponse.getData();
            if (user == null) {
                // 用户名错误
                result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
                result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
            } else {
                // 登录成功
                request.getSession().setAttribute(Constants.SESSION_USER, user);
                result.setCode(ErrorCode.SUCCESS.getCode());
                this.writeResponse(response, result);
                return;
            }
        } else {
            // 用户名错误
            result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
            result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
        }

        this.writeResponse(response, result);
    }

    @Override
    public void userLogout(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws IOException {
        BaseResult result = new BaseResult();

        try {
            request.getSession().setAttribute(Constants.SESSION_USER, null);

            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
        } catch (Exception ex) {
            this.writeResponse(response, result);
        }
    }

}
