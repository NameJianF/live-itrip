package live.itrip.admin.dao;

import live.itrip.admin.model.AdminRolePermission;

public interface AdminRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminRolePermission record);

    int insertSelective(AdminRolePermission record);

    AdminRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminRolePermission record);

    int updateByPrimaryKey(AdminRolePermission record);
}