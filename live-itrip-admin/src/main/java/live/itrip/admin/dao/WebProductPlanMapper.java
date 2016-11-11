package live.itrip.admin.dao;

import live.itrip.admin.model.WebProductPlan;

public interface WebProductPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebProductPlan record);

    int insertSelective(WebProductPlan record);

    WebProductPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebProductPlan record);

    int updateByPrimaryKey(WebProductPlan record);
}