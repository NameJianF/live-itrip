package live.itrip.admin.dao;

import live.itrip.admin.model.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);

    // ================= add ==========
    Integer countAll();

    List<AdminRole> selectRoles(@Param("start") Integer start, @Param("length") Integer length);
}