package live.itrip.admin.dao;

import live.itrip.admin.model.AdminGroup;
import live.itrip.admin.model.extend.ExtendAdminGroup;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AdminGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminGroup record);

    int insertSelective(AdminGroup record);

    AdminGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminGroup record);

    int updateByPrimaryKey(AdminGroup record);

    // ===================== add ============
    List<ExtendAdminGroup> selectAllGroups();

    Integer countAll();

    List<ExtendAdminGroup> selectGroups(@Param("start") Integer start, @Param("length") Integer length);


}