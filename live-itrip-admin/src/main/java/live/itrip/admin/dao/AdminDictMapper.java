package live.itrip.admin.dao;

import java.util.List;

import live.itrip.admin.model.AdminDict;
import org.apache.ibatis.annotations.Param;

public interface AdminDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminDict record);

    int insertSelective(AdminDict record);

    AdminDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminDict record);

    int updateByPrimaryKey(AdminDict record);

    // ==================== add ==========
    Integer countAll();

    List<AdminDict> selectDicts(@Param("queryContent") String queryContent, @Param("start") Integer start, @Param("length") Integer length);

    List<AdminDict> selectAllDicts(@Param("flag") String flag);
}