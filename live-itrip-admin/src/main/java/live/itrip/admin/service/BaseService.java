package live.itrip.admin.service;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.interfaces.IWriteResponse;
import live.itrip.common.Encoding;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 建锋 on 2016/7/7.
 */
public class BaseService implements IWriteResponse {
    @Override
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
            Logger.error(e);
        }
    }

    @Override
    public void paramInvalid(HttpServletResponse response, String paramName) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.PARAM_INVALID.getCode());
        result.setMsg(String.format(ErrorCode.PARAM_INVALID.getMessage() + "(%s).", paramName));

        this.writeResponse(response, result);
    }
}
