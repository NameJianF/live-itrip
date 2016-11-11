package live.itrip.admin.dao;

import live.itrip.admin.model.WebProduct;

public interface WebProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebProduct record);

    int insertSelective(WebProduct record);

    WebProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebProduct record);

    int updateByPrimaryKeyWithBLOBs(WebProduct record);

    int updateByPrimaryKey(WebProduct record);
}