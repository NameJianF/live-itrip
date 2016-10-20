package live.itrip.admin.dao;

import live.itrip.admin.model.AdminDictItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDictItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminDictItem record);

    int insertSelective(AdminDictItem record);

    AdminDictItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminDictItem record);

    int updateByPrimaryKey(AdminDictItem record);

    // ====================== add ===============

    Integer countAll();

    List<AdminDictItem> selectDictItems(@Param("start") Integer start, @Param("length") Integer length);
}