package live.itrip.admin.dao;

import live.itrip.admin.model.WebServiceOrder;

public interface WebServiceOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebServiceOrder record);

    int insertSelective(WebServiceOrder record);

    WebServiceOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebServiceOrder record);

    int updateByPrimaryKey(WebServiceOrder record);
}