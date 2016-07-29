package live.itrip.admin.dao;

import live.itrip.admin.model.AdminPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminPermission record);

    int insertSelective(AdminPermission record);

    AdminPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminPermission record);

    int updateByPrimaryKey(AdminPermission record);

    // -------- add -------------
    List<AdminPermission> selectPermissionsByRoleId(@Param("roleId") Long roleId);

}