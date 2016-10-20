package live.itrip.admin.dao;

import live.itrip.admin.model.AdminGroupDepart;

public interface AdminGroupDepartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminGroupDepart record);

    int insertSelective(AdminGroupDepart record);

    AdminGroupDepart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminGroupDepart record);

    int updateByPrimaryKey(AdminGroupDepart record);
}