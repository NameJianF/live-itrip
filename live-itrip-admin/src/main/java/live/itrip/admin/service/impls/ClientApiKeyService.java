package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.ClientApiKeyMapper;
import live.itrip.admin.model.ClientApiKey;
import live.itrip.admin.request.ApiKeyRequest;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IClientApiKeyService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.common.security.DESUtils;
import org.apache.commons.lang3.StringUtils;
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
public class ClientApiKeyService extends BaseService implements IClientApiKeyService {
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

    @Override
    public void selectApikeys(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<ClientApiKey> result = new BootStrapDataTableList<>();
        try {
            // 解析查询条件
            JSONArray jsonarray = JSONArray.parseArray(decodeJson);
            String queryContent = null;
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
                if (obj.get("name").equals("queryContent")) {
                    queryContent = obj.get("value").toString();
                }
            }
            if (StringUtils.isNotEmpty(queryContent)) {
                queryContent = "'%" + queryContent.trim() + "%'";
            }

            PagerInfo pagerInfo = this.getPagerInfo(jsonarray);
            Integer count = clientApiKeyMapper.countAll();
            List<ClientApiKey> list = clientApiKeyMapper.selectApikeys(queryContent, pagerInfo.getStart(), pagerInfo.getLength());
            if (list != null) {
                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(count);
                result.setiTotalDisplayRecords(count);
                result.setAaData(list);

                // response
                this.writeResponse(response, result);
                return;
            }
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }

        BaseResult error = new BaseResult();
        error.setCode(ErrorCode.UNKNOWN.getCode());
        this.writeResponse(response, error);
    }

    @Override
    public void selectApikeyById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer id = (Integer) jsonObject.get("apikeyId");
        if (id != null) {
            ClientApiKey model = this.clientApiKeyMapper.selectByPrimaryKey(id);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(model);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteApikeyById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer id = (Integer) jsonObject.get("apikeyId");
        if (id != null) {
            ClientApiKey model = new ClientApiKey();
            model.setId(id);
            model.setIsDelete(Constants.FLAG_IS_DELETE);
            Integer ret = this.clientApiKeyMapper.updateByPrimaryKeySelective(model);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                result.setData(model);
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void editApikeyById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        ClientApiKey model = JSON.parseObject(decodeJson, ClientApiKey.class);
        Integer ret;
        if (model.getId() == null) {
            // new
            model.setCreateTime(System.currentTimeMillis());
            model.setUpdateTime(model.getCreateTime());
            ret = this.clientApiKeyMapper.insertSelective(model);
        } else {
            // update
            model.setUpdateTime(System.currentTimeMillis());
            ret = this.clientApiKeyMapper.updateByPrimaryKeySelective(model);
        }
        if (ret > 0) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }
}
