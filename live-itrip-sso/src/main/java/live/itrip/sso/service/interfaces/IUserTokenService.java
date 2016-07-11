package live.itrip.sso.service.interfaces;

import live.itrip.sso.model.UserToken;

/**
 * Created by Feng on 2016/3/8.
 */
public interface IUserTokenService {
    /**
     * 添加usertoken
     *
     * @param record
     * @return
     */
    int insertSelective(UserToken record);
}
