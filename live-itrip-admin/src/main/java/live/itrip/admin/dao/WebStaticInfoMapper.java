package live.itrip.admin.dao;

import live.itrip.admin.model.WebStaticInfo;

public interface WebStaticInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebStaticInfo record);

    int insertSelective(WebStaticInfo record);

    WebStaticInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebStaticInfo record);

    int updateByPrimaryKeyWithBLOBs(WebStaticInfo record);

    int updateByPrimaryKey(WebStaticInfo record);
}