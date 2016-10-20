package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.AdminRole;

import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
public interface IAdminUserRoleService {
    List<AdminRole> selectRolesByUserId(Long userId);
}
