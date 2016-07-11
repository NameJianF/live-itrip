package live.itrip.sso.interfaces;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2016/7/7.
 */
public interface IWriteResponse {
    /**
     * Write Response
     *
     * @param response
     * @param obj
     */
    void writeResponse(HttpServletResponse response, Object obj);

    /**
     * 参数无效
     *
     * @param response
     * @param paramName
     */
    void paramInvalid(HttpServletResponse response, String paramName);

    /**
     * 参数无效
     *
     * @param response
     * @param paramName
     */
    void paramInvalid(HttpServletResponse response, String paramName, String paramValue);
}
