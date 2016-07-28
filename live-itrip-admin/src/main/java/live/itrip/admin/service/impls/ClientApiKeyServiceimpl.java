package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.ClientApiKeyMapper;
import live.itrip.admin.model.ClientApiKey;
import live.itrip.admin.request.ApiKeyRequest;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IClientApiKeyService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import live.itrip.common.security.DESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feng on 2016/7/14.
 * <p>
 * apikey 服务类
 */
@Service
public class ClientApiKeyServiceImpl extends BaseService implements IClientApiKeyService {
    @Autowired
    private ClientApiKeyMapper clientApiKeyMapper;

    /**
     * 查询apikey 列表
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectKeyList(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws Exception {
        BaseResult result = new BaseResult();
        ApiKeyRequest apiKeyRequest = JSON.parseObject(decodeJson, ApiKeyRequest.class);
        result.setOp(apiKeyRequest.getOp());
        // 关键信息加密处理
        List<ClientApiKey> keyList = clientApiKeyMapper.selectAllKeys();
        List<ClientApiKey> keyListCopy = new ArrayList<ClientApiKey>();

        for (ClientApiKey key : keyList) {
            ClientApiKey apiKey = new ClientApiKey();
            apiKey.setApiKey(DESUtils.encryptDES(key.getApiKey(), Constants.DES_KEY));
            apiKey.setSecretKey(DESUtils.encryptDES(key.getSecretKey(), Constants.DES_KEY));
            apiKey.setDescription(key.getDescription());
            apiKey.setClientName(key.getClientName());
            apiKey.setSource(key.getSource());
            apiKey.setCreateTime(key.getCreateTime());
            apiKey.setId(key.getId());
            apiKey.setUpdateTime(key.getUpdateTime());
            keyListCopy.add(apiKey);
        }

        result.setData(keyListCopy);
        result.setCode(ErrorCode.SUCCESS.getCode());
//        result.setMsg("");
        this.writeResponse(response, result);
    }

    /**
     * 查询 ClientApiKey by apikey
     *
     * @param clientapikey
     * @return
     */
    @Override
    public ClientApiKey selectClientKey(String clientapikey) {
        List<ClientApiKey> keyList = clientApiKeyMapper.selectAllKeys();
        if (keyList != null) {
            for (ClientApiKey key : keyList) {
                if (clientapikey.equals(key.getApiKey())) {
                    return key;
                }
            }
        }
        return null;
    }
}
