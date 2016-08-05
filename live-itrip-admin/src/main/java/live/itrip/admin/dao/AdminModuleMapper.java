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

    /**
     * 查询模块信息
     *
     * @param flag 是否标记删除的， 删除：1，正常：0
     * @return
     */
    List<AdminModule> selectModules(@Param("flag") String flag);

    /**
     * 查询全部
     *
     * @return
     */
    List<AdminModule> selectAllModules();
}