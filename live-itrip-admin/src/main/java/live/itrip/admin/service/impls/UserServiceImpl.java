package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.api.sso.SsoApi;
import live.itrip.admin.api.sso.bean.LoginResponse;
import live.itrip.admin.api.sso.bean.User;
import live.itrip.admin.common.Constants;
import live.itrip.admin.model.AdminModule;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminModuleService;
import live.itrip.admin.service.intefaces.IUserService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.common.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2016/7/7.
 * <p>
 * admin  用户操作服务
 */
@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private IAdminModuleService iAdminModuleService;

    @Override
    public void login(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        LoginData loginData = JSON.parseObject(decodeJson, LoginData.class);
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
                // 用户信息正确
                request.getSession().setAttribute(Constants.SESSION_USER, user);
                // 写入 cookie
                CookieUtils.setCookie(request, response, Constants.COOKIE_TOKEN_NAME, user.getToken());

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

    /**
     * 查询模块信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectModules(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();

        try {
            List<AdminModule> list = iAdminModuleService.selectModules("0");
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

    /**
     * login datas
     *
     * @author JianF
     */
    public static class LoginData {
        private String userName;
        private String pwd;
        private String captcha = "";


        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCaptcha() {
            return captcha;
        }

        public void setCaptcha(String captcha) {
            this.captcha = captcha;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

    }
}
