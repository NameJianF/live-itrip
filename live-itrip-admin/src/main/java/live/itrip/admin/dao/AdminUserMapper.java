package live.itrip.admin.dao;

import live.itrip.admin.model.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    // ================= add ===============
    Integer countAll();

    List<AdminUser> selectAdminUsers(@Param("queryDepart") Integer queryDepart, @Param("queryGroup") Integer queryGroup
            , @Param("queryUserName") String queryUserName, @Param("start") Integer start, @Param("length") Integer length);
}