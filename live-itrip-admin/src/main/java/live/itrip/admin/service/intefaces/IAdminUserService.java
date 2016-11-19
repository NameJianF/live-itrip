package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.AdminUser;
import live.itrip.admin.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/10/24.
 */
public interface IAdminUserService {

    AdminUser selectAdminUserById(Long userId);

    AdminUser saveUserInfo(User user);

    void selectAdminUsers(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    String login(String userName, String password);

}
