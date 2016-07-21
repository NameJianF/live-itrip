package live.itrip.admin.dao;

import live.itrip.admin.model.AdminDict;

public interface AdminDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminDict record);

    int insertSelective(AdminDict record);

    AdminDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminDict record);

    int updateByPrimaryKey(AdminDict record);
}