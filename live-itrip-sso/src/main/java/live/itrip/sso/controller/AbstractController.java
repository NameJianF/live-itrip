package live.itrip.sso.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletResponse;

import cn.tourin.tools.SigUtils;
import com.alibaba.fastjson.JSON;

import live.itrip.common.Encoding;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.request.RequestHeader;
import live.itrip.common.response.BaseResult;
import live.itrip.sso.api.admin.bean.ClientApiKey;
import live.itrip.sso.common.Config;
import live.itrip.sso.common.Constants;
import live.itrip.sso.interfaces.IValidateParams;
import live.itrip.sso.model.UserToken;
import live.itrip.sso.service.interfaces.IUserTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractController implements IValidateParams {

    private static final Long TIMEOUT = 1000L * 60;

    @Autowired
    private IUserTokenService iUserTokenService;

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
            Logger.error(Constants.LOG_TAG, e);
        }
    }

    /**
     * op is invalid
     *
     * @param response
     */
    protected void opIsEmpty(HttpServletResponse response) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.UNKNOWN.getCode());
        result.setMsg("op is invalid");

        this.writeResponse(response, result);
    }

    /**
     * json is invalid
     *
     * @param response
     */
    protected void jsonIsEmpty(HttpServletResponse response) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.UNKNOWN.getCode());
        result.setMsg("json is invalid");

        this.writeResponse(response, result);
    }

    /**
     * error params
     *
     * @param response
     * @param error
     */
    protected void writeResponseErrorApp(HttpServletResponse response, BaseResult error) {
        this.writeResponse(response, error);
    }

    @Override
    public BaseResult validateParams(RequestHeader header) {
        BaseResult error = new BaseResult();

        if (header == null) {
            error.setError(ErrorCode.UNKNOWN);
            return error;
        }

        ClientApiKey clientApiKey = validateApiKey(header.getApikey());

        if (clientApiKey == null) {
            error.setCode(ErrorCode.PARAM_INVALID.getCode());
            error.setMsg(String.format("%s(apikey)", ErrorCode.PARAM_INVALID.getMessage()));
            return error;
        }

        if (validateSig(clientApiKey, header)) {
            error.setCode(ErrorCode.PARAM_INVALID.getCode());
            error.setMsg(String.format("%s(sig)", ErrorCode.PARAM_INVALID.getMessage()));
            return error;
        }

        if (validateTimestamp(header.getTimestamp())) {
            error.setCode(ErrorCode.PARAM_INVALID.getCode());
            error.setMsg(String.format("%s(timestamp)", ErrorCode.PARAM_INVALID.getMessage()));
            return error;
        }

        if (validateToken(header.getSid())) {
            error.setCode(ErrorCode.PARAM_INVALID.getCode());
            error.setMsg(String.format("%s(sid)", ErrorCode.PARAM_INVALID.getMessage()));
            return error;
        }

        error.setError(ErrorCode.SUCCESS);
        return error;
    }

    private boolean validateSig(ClientApiKey clientApiKey, RequestHeader header) {
        if (StringUtils.isEmpty(header.getSig())) {
            return false;
        }
        String secretkey = clientApiKey.getSecretKey();

        // 计算sig
        String sig = SigUtils.getSig(header, secretkey);
        if (!sig.equals(header.getSig())) {
            return false;
        }

        return true;
    }

    private ClientApiKey validateApiKey(String apiKey) {
        if (StringUtils.isEmpty(apiKey)) {
            return null;
        }
        for (ClientApiKey client : Config.LIST_APIKEY) {
            if (client.getSecretKey().equals(apiKey)) {
                return client;
            }
        }

        return null;
    }

    /**
     * 校验时间戳
     *
     * @param timestamp
     * @return
     */
    private boolean validateTimestamp(Long timestamp) {
        if (timestamp == null) {
            return false;
        }
        if (System.currentTimeMillis() - timestamp > TIMEOUT) {
            // 时间超时
            return false;
        }
        return true;
    }

    private boolean validateToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        UserToken userToken = this.iUserTokenService.selectByToken(token);
        if (userToken == null
                || userToken.getExpireTime() != 0  // 启用超时设置
                && userToken.getExpireTime() < System.currentTimeMillis()) {
            // 时间超时
            return false;
        }
        return true;
    }


}
