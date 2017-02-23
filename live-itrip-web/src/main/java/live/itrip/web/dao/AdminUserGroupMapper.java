package live.itrip.web.dao;

import live.itrip.web.model.AdminUserGroup;

public interface AdminUserGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUserGroup record);

    int insertSelective(AdminUserGroup record);

    AdminUserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUserGroup record);

    int updateByPrimaryKey(AdminUserGroup record);
}