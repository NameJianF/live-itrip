package live.itrip.admin.dao;

import org.apache.ibatis.annotations.Param;

import live.itrip.admin.model.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	// ---------- add --------
	/**
	 * select by username email mobile
	 * 
	 * @param userName
	 * @return
	 */
	User selectByUserName(@Param("userName") String userName);
}