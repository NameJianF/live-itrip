package live.itrip.sso.service.impls;

import live.itrip.sso.common.Config;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Feng on 2016/3/8.
 * 1. 调用admin接口验证apikey合法性
 * 2. 缓存合法的apikey
 */
@Service("initService")
public class InitService {


    public InitService() {
        initApiKeys();
    }

    /**
     * 获取apikey
     */
    private void initApiKeys() {
        Config.API_KEY.put("701917a6eca24a14b9246bb331e144d8", "91E4CDC17809A8D5");

        //TODO 调用admin服务获取apikey配置信息

    }
}
