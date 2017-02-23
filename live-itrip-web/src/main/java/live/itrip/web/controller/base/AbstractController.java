package live.itrip.web.controller.base;


import com.alibaba.fastjson.JSON;
import live.itrip.common.Encoding;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.web.interfaces.IWriteResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Feng on 2016/7/5.
 * <p>
 * Controller 基类
 */
public abstract class AbstractController implements IWriteResponse {

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
