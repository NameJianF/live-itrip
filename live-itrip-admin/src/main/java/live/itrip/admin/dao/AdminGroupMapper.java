package live.itrip.admin.dao;

import live.itrip.admin.model.AdminGroup;

public interface AdminGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminGroup record);

    int insertSelective(AdminGroup record);

    AdminGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminGroup record);

    int updateByPrimaryKey(AdminGroup record);
}