package live.itrip.web.service.intefaces;

import live.itrip.web.model.AdminRole;

import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
public interface IAdminUserRoleService {
    List<AdminRole> selectRolesByUserId(Long userId);
}
