package live.itrip.admin.dao;

import live.itrip.admin.model.AdminModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminModule record);

    int insertSelective(AdminModule record);

    AdminModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminModule record);

    int updateByPrimaryKey(AdminModule record);

    // ======================= add ==============

    List<AdminModule> selectAllModules(@Param("flag") String flag);

    List<AdminModule> selectModulesByIds(@Param("listModuleId") List<Integer> listModuleId);

    List<AdminModule> selectModules(@Param("queryContent") String queryContent, @Param("start") Integer start, @Param("length") Integer length);

    Integer countAll();
}