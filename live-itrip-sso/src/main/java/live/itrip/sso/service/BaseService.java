package live.itrip.sso.service;

import com.alibaba.fastjson.JSON;
import live.itrip.common.Encoding;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.sso.common.Constants;
import live.itrip.sso.interfaces.IWriteResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseService implements IWriteResponse {

    public BaseService() {
        super();
    }

    /**
     * @param response
     * @param obj
     */
    public void writeResponse(HttpServletResponse response, Object obj) {
        try {
            String json = JSON.toJSONString(obj);
            Logger.debug("Write Response Json:" + json);
            response.setCharacterEncoding(Encoding.UTF8);
            PrintWriter out;
            out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            Logger.error(Constants.LOG_TAG, e);
        }
    }

    @Override
    public void paramInvalid(HttpServletResponse response, String paramName, String paramValue) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.PARAM_INVALID.getCode());
        result.setMsg(String.format(ErrorCode.PARAM_INVALID.getMessage() + "%s(%s).", paramName, paramValue));

        this.writeResponse(response, result);
    }

    @Override
    public void paramInvalid(HttpServletResponse response, String paramName) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.PARAM_INVALID.getCode());
        result.setMsg(String.format(ErrorCode.PARAM_INVALID.getMessage() + "(%s).", paramName));

        this.writeResponse(response, result);
    }


}
