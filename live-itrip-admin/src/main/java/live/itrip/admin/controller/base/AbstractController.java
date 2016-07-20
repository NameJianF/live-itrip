package live.itrip.admin.controller.base;


import com.alibaba.fastjson.JSON;
import live.itrip.admin.interfaces.IWriteResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

import live.itrip.admin.model.ClientApiKey;
import live.itrip.admin.service.intefaces.IClientApiKeyService;
import live.itrip.common.Encoding;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.request.RequestHeader;
import live.itrip.common.response.BaseResult;
import live.itrip.common.util.SigUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Feng on 2016/7/5.
 * <p>
 * Controller 基类
 */
public abstract class AbstractController implements IWriteResponse {
    private static final Long TIMEOUT = 1000L * 60;

    @Autowired
    private IClientApiKeyService iClientApiKeyService;

    /**
     * 根据 apikey 验证客户端
     *
     * @return
     */
    protected ClientApiKey validateClientApiKey(String clientapikey) {
        if (StringUtils.isEmpty(clientapikey)) {
            return null;
        }
        ClientApiKey client = iClientApiKeyService.selectClientKey(clientapikey);
        if (client != null && StringUtils.isNotEmpty(client.getSecretKey())) {
            return client;
        }
        return null;
    }

    /**
     * 校验用户 sig
     *
     * @param baseClientKey
     * @param header
     */
    public boolean validateSig(ClientApiKey baseClientKey, RequestHeader header, String jsonString) {
        String secretkey = baseClientKey.getSecretKey();

        // 计算sig
        String sig = SigUtils.getSig(jsonString, secretkey);
        if (!sig.equals(header.getSig())) {
            return false;
        }

        return true;
    }

    /**
     * 校验时间戳
     *
     * @param timestamp
     * @return
     */
    public boolean validateTimestamp(Long timestamp) {
        if (timestamp == null) {
            return false;
        }
        if (System.currentTimeMillis() - timestamp > TIMEOUT) {
            // 时间超时
            return false;
        }
        return true;
    }


    /**
     * 校验用户token
     *
     * @param usertoken
     * @return
     */
    public boolean validateToken(String usertoken) {
//        if (usertoken == null) {
//            return false;
//        }
//        Long expiretime = Long.valueOf(usertoken.getExpiretime());
//        if (expiretime != 0 && expiretime < System.currentTimeMillis()) {
//            // 时间超时
//            return false;
//        }
        return true;
    }


    @Override
    public void writeResponse(HttpServletResponse response, Object obj) {
        try {
            AtomicReference<String> json = new AtomicReference<String>(JSON.toJSONString(obj));
            response.setCharacterEncoding(Encoding.UTF8);
            PrintWriter out;
            out = response.getWriter();
            out.print(json.get());
            out.flush();
            out.close();
        } catch (IOException e) {
            Logger.error(e);
        }
    }

    /**
     * 参数无效
     *
     * @param response
     * @param paramName
     */
    @Override
    public void paramInvalid(HttpServletResponse response, String paramName) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.PARAM_INVALID.getCode());
        result.setMsg(String.format(ErrorCode.PARAM_INVALID.getMessage() + "(%s).", paramName));

        this.writeResponse(response, result);
    }
}
