package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.AdminUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

}
