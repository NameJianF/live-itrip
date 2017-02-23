package live.itrip.web.dao;

import live.itrip.web.model.WebProductPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebProductPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebProductPlan record);

    int insertSelective(WebProductPlan record);

    WebProductPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebProductPlan record);

    int updateByPrimaryKey(WebProductPlan record);

    // ================ add =========

    Integer countAll();

    List<WebProductPlan> selectPlanDetails(@Param("start") int start, @Param("length") int length);

    List<WebProductPlan> selectPlanDetailsByProductId(@Param("productId") int productId);

    Integer countByProductId(@Param("productId") int productId);
}