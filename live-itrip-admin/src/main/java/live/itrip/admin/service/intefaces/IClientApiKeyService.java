package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/7/14.
 */
public interface IClientApiKeyService {
    /**
     * 查询apikey 列表
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void selectKeyList(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
