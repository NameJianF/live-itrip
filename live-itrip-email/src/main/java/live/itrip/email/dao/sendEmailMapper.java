package live.itrip.email.dao;

import live.itrip.email.model.SendEmail;

public interface SendEmailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SendEmail record);

    int insertSelective(SendEmail record);

    SendEmail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendEmail record);

    int updateByPrimaryKeyWithBLOBs(SendEmail record);

    int updateByPrimaryKey(SendEmail record);
}