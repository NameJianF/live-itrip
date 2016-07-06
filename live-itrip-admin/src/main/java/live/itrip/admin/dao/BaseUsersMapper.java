package live.itrip.admin.dao;

import org.apache.ibatis.annotations.Param;

import live.itrip.admin.model.BaseUsers;

public interface BaseUsersMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(BaseUsers record);

	int insertSelective(BaseUsers record);

	BaseUsers selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(BaseUsers record);

	int updateByPrimaryKey(BaseUsers record);

	// ---------- add --------
	/**
	 * select by username email mobile
	 * 
	 * @param userName
	 * @return
	 */
	BaseUsers selectByUserName(@Param("userName") String userName);
}