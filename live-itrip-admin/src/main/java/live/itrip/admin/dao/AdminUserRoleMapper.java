package live.itrip.admin.dao;

import live.itrip.admin.model.AdminUserRole;

public interface AdminUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminUserRole record);

    int insertSelective(AdminUserRole record);

    AdminUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUserRole record);

    int updateByPrimaryKey(AdminUserRole record);
}