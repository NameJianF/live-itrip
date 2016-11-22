package live.itrip.admin.dao;

import live.itrip.admin.model.WebServiceOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebServiceOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebServiceOrder record);

    int insertSelective(WebServiceOrder record);

    WebServiceOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebServiceOrder record);

    int updateByPrimaryKey(WebServiceOrder record);

    // ================ add ==========
    Integer countAll();

    List<WebServiceOrder> selectOrderList(@Param("start") int start, @Param("length") int length);
}