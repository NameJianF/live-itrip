package live.itrip.web.service.intefaces;

import live.itrip.web.model.AdminUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/10/24.
 */
public interface IAdminUserService {

    AdminUser selectAdminUserById(Long userId);

    AdminUser saveUserInfo(AdminUser user);

    void selectAdminUsers(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    int editAdminUser(AdminUser user);

    void userLogin(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void userLogout(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectModulesByUser(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void updatePassword(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void register(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
