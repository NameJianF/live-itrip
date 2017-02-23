package live.itrip.sso.service.interfaces;

import live.itrip.common.response.BaseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2017/2/21.
 */
public interface ISsoService {
    /**
     * login
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void login(String decodeJson, HttpServletResponse response,
               HttpServletRequest request);

    BaseResult login(String email, String pwd, String apikey, String source, String host, String clientVersion);

    /**
     * logout
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void logout(String decodeJson, HttpServletResponse response,
                HttpServletRequest request);

    /**
     * 用户鉴权
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void authUser(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
