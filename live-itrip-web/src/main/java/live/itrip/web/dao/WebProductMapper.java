package live.itrip.web.dao;

import live.itrip.web.model.WebProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebProduct record);

    int insertSelective(WebProduct record);

    WebProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebProduct record);

    int updateByPrimaryKeyWithBLOBs(WebProduct record);

    int updateByPrimaryKey(WebProduct record);

    // ================== add ==========
    Integer countAll();

    List<WebProduct> selectProducts(@Param("start") int start, @Param("length") int length);

    List<WebProduct> selectListByType(@Param("topCount") int topCount, @Param("type") String type);

    List<WebProduct> selectListAbouts(@Param("topCount") int topCount, @Param("productId") Integer productId, @Param("type") String type);

    List<WebProduct> selectHotProductsByCity(@Param("topCount") int topCount, @Param("cityName") String cityName);

    List<WebProduct> selectListByTypeAndCity(@Param("type") String type, @Param("cityName") String cityName);
}