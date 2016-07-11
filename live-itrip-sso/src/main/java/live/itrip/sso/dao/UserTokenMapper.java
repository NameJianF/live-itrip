package live.itrip.sso.dao;


import live.itrip.sso.model.UserToken;

public interface UserTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserToken record);

    int insertSelective(UserToken record);

    UserToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserToken record);

    int updateByPrimaryKey(UserToken record);
}