package live.itrip.sso.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import live.itrip.common.Encoding;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.request.RequestHeader;
import live.itrip.common.response.BaseResult;
import live.itrip.sso.common.Constants;
import live.itrip.sso.interfaces.IValidateParams;
import org.apache.commons.lang3.StringUtils;

public class BaseController implements IValidateParams {

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

            return error;
        }
        String sig = header.getSig();
        if (StringUtils.isEmpty(sig)) {
            return error;
        }

        String apikey = header.getApikey();
        if (StringUtils.isEmpty(apikey)) {
            return error;
        }

        String token = header.getSid();
        if (StringUtils.isEmpty(token)) {
            return error;
        }

        return error;
    }

    private boolean validateSig(String sig) {
        return true;
    }

    private boolean validateApiKey(String apiKey) {
        return true;
    }

    private boolean validateToken(String token) {
        return true;
    }


}
