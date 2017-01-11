package live.itrip.admin.dao;

import java.util.List;

import live.itrip.admin.model.ClientApiKey;
import org.apache.ibatis.annotations.Param;

public interface ClientApiKeyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClientApiKey record);

    int insertSelective(ClientApiKey record);

    ClientApiKey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClientApiKey record);

    int updateByPrimaryKey(ClientApiKey record);

    // ================== add ==============

    /**
     * 查询全部apikey
     *
     * @return
     */
    List<ClientApiKey> selectAllKeys();

    Integer countAll();

    List<ClientApiKey> selectApikeys(@Param("queryContent") String queryContent, @Param("start") Integer start, @Param("length") Integer length);
}