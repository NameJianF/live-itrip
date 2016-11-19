package live.itrip.admin.dao;


import live.itrip.admin.model.UserToken;
import org.apache.ibatis.annotations.Param;

public interface UserTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserToken record);

    int insertSelective(UserToken record);

    UserToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserToken record);

    int updateByPrimaryKey(UserToken record);

    UserToken selectByToken(@Param("token") String token);
}