package live.itrip.web.dao;

import live.itrip.web.model.AdminUserPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminUserPermission record);

    int insertSelective(AdminUserPermission record);

    AdminUserPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUserPermission record);

    int updateByPrimaryKey(AdminUserPermission record);

    // ======================== add =================
    List<AdminUserPermission> selectUserPermissionsByUserId(@Param("uid") Long uid);
}