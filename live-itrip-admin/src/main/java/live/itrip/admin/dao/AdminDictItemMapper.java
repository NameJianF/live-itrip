package live.itrip.admin.dao;

import live.itrip.admin.model.AdminDictItem;

public interface AdminDictItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminDictItem record);

    int insertSelective(AdminDictItem record);

    AdminDictItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminDictItem record);

    int updateByPrimaryKey(AdminDictItem record);
}