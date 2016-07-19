package live.itrip.sso.api.admin;

import com.alibaba.fastjson.JSON;
import live.itrip.common.Logger;
import live.itrip.common.request.RequestHeader;
import live.itrip.common.security.DESUtils;
import live.itrip.sso.api.AbstractApi;
import live.itrip.sso.api.admin.bean.ClientApiKey;
import live.itrip.sso.api.admin.bean.ClientApiKeyResponse;
import live.itrip.sso.common.Config;
import live.itrip.sso.common.Constants;

import java.util.List;

/**
 * Created by Feng on 2016/7/15.
 */
public class AdminApi extends AbstractApi {

    private static final String ACTION_APIKEY_URL = "apikeys.action";

    /**
     * 获取啊皮keys 列表
     *
     * @return
     */
    public void selectApiKeys() throws Exception {

        RequestHeader header = new RequestHeader();
        header.setApikey(Config.MODULE_APP_APIKEY);
        header.setOp("ApiKey.list");
        header.setTimestamp(System.currentTimeMillis());

        String res = postJsonString(header, Config.ADMIN_URL, ACTION_APIKEY_URL);
        ClientApiKeyResponse clientApiKeyResponse = JSON.parseObject(res, ClientApiKeyResponse.class);

        if (clientApiKeyResponse != null
                && clientApiKeyResponse.getCode() != null
                && clientApiKeyResponse.getCode() == 0) {

            if (clientApiKeyResponse.getData() != null) {
                List<ClientApiKey> list = clientApiKeyResponse.getData();
                for (ClientApiKey apiKey : list) {
                    apiKey.setApiKey(DESUtils.decryptDES(apiKey.getApiKey(), Constants.DES_KEY));
                    apiKey.setSecretKey(DESUtils.decryptDES(apiKey.getSecretKey(), Constants.DES_KEY));
                }
                Config.LIST_APIKEY = list;
                Logger.info(JSON.toJSONString(Config.LIST_APIKEY));
            }
        }
    }
}
