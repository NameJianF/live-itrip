package live.itrip.web.dao;

import live.itrip.web.model.WebFileInfo;

public interface WebFileInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WebFileInfo record);

    int insertSelective(WebFileInfo record);

    WebFileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebFileInfo record);

    int updateByPrimaryKey(WebFileInfo record);
}