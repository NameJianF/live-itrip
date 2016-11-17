package live.itrip.admin.dao;

import live.itrip.admin.model.WebFileInfo;

public interface WebFileInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WebFileInfo record);

    int insertSelective(WebFileInfo record);

    WebFileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebFileInfo record);

    int updateByPrimaryKey(WebFileInfo record);
}