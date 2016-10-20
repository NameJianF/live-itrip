package live.itrip.admin.dao;

import live.itrip.admin.model.AdminDepart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDepartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminDepart record);

    int insertSelective(AdminDepart record);

    AdminDepart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminDepart record);

    int updateByPrimaryKey(AdminDepart record);

    // ================ add ===========
    Integer countAll();

    List<AdminDepart> selectDeparts(@Param("start") Integer start, @Param("length") Integer length);
}