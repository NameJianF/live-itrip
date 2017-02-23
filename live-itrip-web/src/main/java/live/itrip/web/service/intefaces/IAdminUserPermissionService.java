package live.itrip.web.service.intefaces;

import live.itrip.web.model.AdminUserPermission;

import java.util.List;

/**
 * Created by Feng on 2016/10/12.
 */
public interface IAdminUserPermissionService {
    List<AdminUserPermission> selectUserPermissionsByUserId(Long userId);
}
