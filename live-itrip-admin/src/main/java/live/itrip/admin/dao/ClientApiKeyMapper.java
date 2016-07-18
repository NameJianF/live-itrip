package live.itrip.admin.dao;

import java.util.List;

import live.itrip.admin.model.ClientApiKey;

public interface ClientApiKeyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClientApiKey record);

    int insertSelective(ClientApiKey record);

    ClientApiKey selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClientApiKey record);

    int updateByPrimaryKey(ClientApiKey record);

    /**
     * 查询全部apikey
     *
     * @return
     */
    List<ClientApiKey> selectAllKeys();
}