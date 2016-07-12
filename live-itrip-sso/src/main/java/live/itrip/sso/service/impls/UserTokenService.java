package live.itrip.sso.service.impls;

import live.itrip.sso.dao.UserTokenMapper;
import live.itrip.sso.model.UserToken;
import live.itrip.sso.service.BaseService;
import live.itrip.sso.service.interfaces.IUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Feng on 2016/3/8.
 */
@Service
public class UserTokenService extends BaseService implements IUserTokenService {


    @Autowired
    private UserTokenMapper userTokenMapper;


    /**
     * 记录用户登录的token
     *
     * @param record
     * @return
     */
    @Override
    public int insertSelective(UserToken record) {
        return userTokenMapper.insertSelective(record);
    }
}
