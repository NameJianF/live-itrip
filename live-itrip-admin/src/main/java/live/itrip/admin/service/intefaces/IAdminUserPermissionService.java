package live.itrip.admin.service.intefaces;

import live.itrip.admin.api.sso.bean.User;
import live.itrip.admin.model.AdminUserPermission;

import java.util.List;

/**
 * Created by Feng on 2016/10/12.
 */
public interface IAdminUserPermissionService {
    List<AdminUserPermission> selectUserPermissionsByUserId(Long userId);
}
