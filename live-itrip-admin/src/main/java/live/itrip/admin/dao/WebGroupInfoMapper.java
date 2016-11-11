package live.itrip.admin.dao;

import live.itrip.admin.model.WebGroupInfo;

public interface WebGroupInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebGroupInfo record);

    int insertSelective(WebGroupInfo record);
}