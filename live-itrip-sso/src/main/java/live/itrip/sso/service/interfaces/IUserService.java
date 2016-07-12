package live.itrip.sso.service.interfaces;


import live.itrip.sso.common.exception.ApiException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/2/14.
 */
public interface IUserService {
    void test();

    void register(String decodeJson, HttpServletResponse response,
                  HttpServletRequest request) throws ApiException;

    void updatePassword(String decodeJson, HttpServletResponse response,
                        HttpServletRequest request) throws ApiException;

    void updateUserInfo(String decodeJson, HttpServletResponse response,
                        HttpServletRequest request) throws ApiException;

    void retrievePassword(String decodeJson, HttpServletResponse response,
                          HttpServletRequest request) throws ApiException;


}
