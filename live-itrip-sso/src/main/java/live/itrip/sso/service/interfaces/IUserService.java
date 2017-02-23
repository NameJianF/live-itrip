package live.itrip.sso.service.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2017/2/21.
 */
public interface IUserService {
    void test();

    void register(String decodeJson, HttpServletResponse response,
                  HttpServletRequest request);

    void updatePassword(String decodeJson, HttpServletResponse response,
                        HttpServletRequest request);

    void updateUserInfo(String decodeJson, HttpServletResponse response,
                        HttpServletRequest request);

    void retrievePassword(String decodeJson, HttpServletResponse response,
                          HttpServletRequest request);
}
