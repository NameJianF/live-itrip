package live.itrip.web.dao;

import live.itrip.web.model.AdminRole;
import live.itrip.web.model.AdminUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminUserRole record);

    int insertSelective(AdminUserRole record);

    AdminUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUserRole record);

    int updateByPrimaryKey(AdminUserRole record);

    // =============== add =========
    List<AdminRole> selectRoleNameByUserId(@Param("uid") Long uid);
}