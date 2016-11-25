package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.AdminUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Feng on 2016/10/12.
 */
public interface IUserService {
    /**
     * 获取当前登录用户
     *
     * @return
     */
    AdminUser getCurrentLoginUser();

    void userLogin(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectModulesByUser(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void userLogout(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws IOException;

    void register(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void updatePassword(String decodeJson, HttpServletResponse response,
                        HttpServletRequest request);

    void updateUserInfo(String decodeJson, HttpServletResponse response,
                        HttpServletRequest request);

    void retrievePassword(String decodeJson, HttpServletResponse response,
                          HttpServletRequest request);

}
