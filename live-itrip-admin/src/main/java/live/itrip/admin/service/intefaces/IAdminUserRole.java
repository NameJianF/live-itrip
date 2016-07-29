package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.AdminRole;

import java.util.List;

/**
 * Created by Feng on 2016/7/29.
 */
public interface IAdminUserRole {
    List<AdminRole> selectRolesByUserId(Long userId);
}
