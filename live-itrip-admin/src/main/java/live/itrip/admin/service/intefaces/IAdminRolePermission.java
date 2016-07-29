package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.AdminPermission;

import java.util.List;

/**
 * Created by Feng on 2016/7/29.
 */
public interface IAdminRolePermission {
    List<AdminPermission> selectPermissionsByRoleId(Long roleId);
}
