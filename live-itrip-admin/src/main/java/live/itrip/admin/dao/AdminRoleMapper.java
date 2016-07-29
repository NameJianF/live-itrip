package live.itrip.admin.dao;

import live.itrip.admin.model.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);

    //     --------- add -------
    List<AdminRole> selectRolesByUserId(@Param("userId") Long userId);
}