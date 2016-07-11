package live.itrip.sso.service.interfaces;


import live.itrip.sso.common.exception.ApiException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/3/8.
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
               HttpServletRequest request) throws ApiException, ApiException;

    /**
     * logout
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void logout(String decodeJson, HttpServletResponse response,
                HttpServletRequest request) throws ApiException;

    /**
     * 用户鉴权
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void authUser(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws ApiException;
}
