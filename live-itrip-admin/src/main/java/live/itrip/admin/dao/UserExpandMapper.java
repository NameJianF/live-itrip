package live.itrip.admin.dao;

import live.itrip.admin.model.UserExpand;

public interface UserExpandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserExpand record);

    int insertSelective(UserExpand record);

    UserExpand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserExpand record);

    int updateByPrimaryKey(UserExpand record);
}