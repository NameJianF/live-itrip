package live.itrip.web.dao;

import live.itrip.web.model.WebFaq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebFaqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebFaq record);

    int insertSelective(WebFaq record);

    WebFaq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebFaq record);

    int updateByPrimaryKey(WebFaq record);


    // ======== add  ======
    Integer countAll();

    List<WebFaq> selectFaqList(@Param("start") int start, @Param("length") int length);
}