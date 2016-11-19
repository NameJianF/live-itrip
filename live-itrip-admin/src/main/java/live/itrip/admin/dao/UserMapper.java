package live.itrip.admin.dao;

import live.itrip.admin.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // ------------ add ----------
    User selectByUserName(@Param("userName") String userName);

    int updatePasswordById(@Param("uid") Long id, @Param("pwd") String pwd);
}