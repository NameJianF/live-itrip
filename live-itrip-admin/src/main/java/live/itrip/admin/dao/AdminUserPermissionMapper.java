package live.itrip.admin.dao;

import live.itrip.admin.model.AdminUserPermission;

import java.util.List;

public interface AdminUserPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminUserPermission record);

    int insertSelective(AdminUserPermission record);

    AdminUserPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUserPermission record);

    int updateByPrimaryKey(AdminUserPermission record);

    // ======================== add =================
    List<AdminUserPermission> selectUserPermissionsByUserId(Long userId);
}