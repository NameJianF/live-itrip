package live.itrip.web.dao;

import live.itrip.web.model.WebGroupInfo;

public interface WebGroupInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebGroupInfo record);

    int insertSelective(WebGroupInfo record);
}