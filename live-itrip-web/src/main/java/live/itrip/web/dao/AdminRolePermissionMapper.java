package live.itrip.web.dao;

import live.itrip.web.model.AdminRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AdminRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRolePermission record);

    int insertSelective(AdminRolePermission record);

    AdminRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRolePermission record);

    int updateByPrimaryKey(AdminRolePermission record);

    // ================= add ================
    List<HashMap<String, Object>> selectPermissionsByRoleId(@Param("roleId") Integer roleId);

    Integer deleteByRoleId(@Param("roleId") Integer roleId);

    Integer batchInsert(@Param("list") List<AdminRolePermission> list);
}