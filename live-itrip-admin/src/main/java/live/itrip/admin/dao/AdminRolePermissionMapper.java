package live.itrip.admin.dao;

import live.itrip.admin.model.AdminPermission;
import live.itrip.admin.model.AdminRolePermission;

import java.util.List;

public interface AdminRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminRolePermission record);

    int insertSelective(AdminRolePermission record);

    AdminRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminRolePermission record);

    int updateByPrimaryKey(AdminRolePermission record);

    // --------- add ---------
}