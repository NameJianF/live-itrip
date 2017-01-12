package live.itrip.admin.dao;

import live.itrip.admin.model.AdminOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminOperation record);

    int insertSelective(AdminOperation record);

    AdminOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminOperation record);

    int updateByPrimaryKey(AdminOperation record);

    // =============== add ===============
    Integer countAll();

    List<AdminOperation> selectOperations(@Param("start") Integer start, @Param("length") Integer length);

    List<AdminOperation> selectAllOperations();

}