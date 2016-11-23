package live.itrip.admin.dao;

import live.itrip.admin.model.WebCityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebCityInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebCityInfo record);

    int insertSelective(WebCityInfo record);

    WebCityInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebCityInfo record);

    int updateByPrimaryKeyWithBLOBs(WebCityInfo record);

    int updateByPrimaryKey(WebCityInfo record);

    // ============ add ==========
    Integer countAll();

    List<WebCityInfo> selectCityInfoList(@Param("start") int start, @Param("length") int length, @Param("queryContent") String queryContent);
}