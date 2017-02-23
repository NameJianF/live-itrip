package live.itrip.web.dao;

import live.itrip.web.model.WebGroupDetail;

public interface WebGroupDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebGroupDetail record);

    int insertSelective(WebGroupDetail record);

    WebGroupDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebGroupDetail record);

    int updateByPrimaryKey(WebGroupDetail record);
}