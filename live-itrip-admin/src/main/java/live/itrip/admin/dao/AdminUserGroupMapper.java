package live.itrip.admin.dao;

import live.itrip.admin.model.AdminUserGroup;

public interface AdminUserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUserGroup record);

    int insertSelective(AdminUserGroup record);

    AdminUserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUserGroup record);

    int updateByPrimaryKey(AdminUserGroup record);
}