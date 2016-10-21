package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.ClientApiKey;

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
    void selectKeyList(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws Exception;

    ClientApiKey selectClientKey(String clientapikey);


    void selectApikeys(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectApikeyById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteApikeyById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editApikeyById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
