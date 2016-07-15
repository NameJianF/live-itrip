package live.itrip.admin.api.sso;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.api.AbstractApi;
import live.itrip.admin.api.sso.bean.LoginRequest;
import live.itrip.admin.api.sso.bean.LoginResponse;

/**
 * Created by Feng on 2016/7/14.
 */
public class SsoApi extends AbstractApi {
    private static final String USER_ACTION = "user.action";

    /**
     * 用户登录
     *
     * @param email
     * @param password
     * @return
     */
    public LoginResponse userLogin(String email, String password) {

        LoginRequest loginRequest = new LoginRequest();
        LoginRequest.LoginData data = new LoginRequest.LoginData();
        data.setEmail(email);
        data.setPassword(password);

        String res = postJsonString(loginRequest, USER_ACTION);
        LoginResponse loginResponse = JSON.parseObject(res, LoginResponse.class);

        return loginResponse;
    }
}
