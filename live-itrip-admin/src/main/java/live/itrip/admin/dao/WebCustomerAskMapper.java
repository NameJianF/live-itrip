package live.itrip.admin.dao;

import live.itrip.admin.model.WebCustomerAsk;

public interface WebCustomerAskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebCustomerAsk record);

    int insertSelective(WebCustomerAsk record);

    WebCustomerAsk selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebCustomerAsk record);

    int updateByPrimaryKey(WebCustomerAsk record);
}