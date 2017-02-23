package live.itrip.web.dao;

import live.itrip.web.model.WebCustomerAsk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebCustomerAskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebCustomerAsk record);

    int insertSelective(WebCustomerAsk record);

    WebCustomerAsk selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebCustomerAsk record);

    int updateByPrimaryKey(WebCustomerAsk record);

    // =============== add ===========
    Integer countAll();

    List<WebCustomerAsk> selectCustomerAskList(@Param("start") int start, @Param("length") int length);
}