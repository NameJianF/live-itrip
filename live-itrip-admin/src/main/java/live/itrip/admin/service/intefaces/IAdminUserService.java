package live.itrip.admin.service.intefaces;

import live.itrip.admin.api.sso.bean.User;
import live.itrip.admin.model.AdminUser;

/**
 * Created by Feng on 2016/10/24.
 */
public interface IAdminUserService {

    AdminUser selectAdminUserById(Long userId);

    AdminUser saveUserInfo(User user);
}
