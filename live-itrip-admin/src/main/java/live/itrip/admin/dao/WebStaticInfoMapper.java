package live.itrip.admin.dao;

import live.itrip.admin.model.WebStaticInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebStaticInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebStaticInfo record);

    int insertSelective(WebStaticInfo record);

    WebStaticInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebStaticInfo record);

    int updateByPrimaryKeyWithBLOBs(WebStaticInfo record);

    int updateByPrimaryKey(WebStaticInfo record);

    // ================ add ==========
    Integer countAll();

    List<WebStaticInfo> selectStaticInfos(@Param("start") int start, @Param("length") int length, @Param("queryContent") String queryContent);

    List<WebStaticInfo> selectAllStaticInfos();

    List<WebStaticInfo> selectAllIdAndTitle();

}