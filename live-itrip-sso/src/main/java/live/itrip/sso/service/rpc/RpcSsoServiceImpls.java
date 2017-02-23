package live.itrip.sso.service.rpc;

import com.alibaba.fastjson.JSON;
import live.itrip.common.response.BaseResult;
import live.itrip.sso.rpc.service.RpcSsoService;
import live.itrip.sso.service.interfaces.ISsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Feng on 2017/2/21.
 * <p/>
 * rpc implements
 */
@Service
public class RpcSsoServiceImpls implements RpcSsoService {

    @Autowired
    private ISsoService iSsoService;

    @Override
    public String login(String email, String password, String apikey, String source, String host, String clientVersion) {
        BaseResult result = iSsoService.login(email, password, apikey, source, host, clientVersion);
        return JSON.toJSONString(result);
//        return "hello";
    }


}
