package live.itrip.admin.dao;

import live.itrip.admin.model.UserOnline;
import org.apache.ibatis.annotations.Param;

public interface UserOnlineMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserOnline record);

    int insertSelective(UserOnline record);

    UserOnline selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserOnline record);

    int updateByPrimaryKey(UserOnline record);

    // ----------- add -----------

    /**
     * delete online user
     *
     * @param uid
     * @param email
     */
    int deleteByUserIdOrUserEmail(@Param("uid") Long uid, @Param("email") String email);
}