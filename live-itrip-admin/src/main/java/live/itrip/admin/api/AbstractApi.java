package live.itrip.admin.api;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.common.Config;
import live.itrip.common.Logger;
import live.itrip.common.http.HttpUtils;
import live.itrip.common.util.SigUtils;

/**
 * Created by Feng on 2016/7/15.
 * <p>
 * api 数据提交
 */
public abstract class AbstractApi {

    /**
     * post　json
     *
     * @param reqObj
     * @param action
     * @return
     */
    public String postJsonString(Object reqObj, String action) {
        return postJsonString(reqObj, Config.SSO_URL, action);
    }

    /**
     * post　json
     *
     * @param reqObj
     * @param postUrl
     * @param action
     * @return
     */
    public String postJsonString(Object reqObj, String postUrl, String action) {
        String strResponse = "";
        String url = "";
        try {
            // 获取sig
            String sig = SigUtils.getSig(JSON.toJSONString(reqObj), Config.MODULE_APP_SECRET);
            // 设置sig
            reqObj.getClass().getMethod("setSig", String.class)
                    .invoke(reqObj, sig);
            String json = JSON.toJSONString(reqObj);
            url = postUrl + action;
            Logger.debug(String.format("URL:%s,Request JSON:%s", url, json));
            strResponse = HttpUtils.httpRequest(url, HttpUtils.REQUEST_METHOD_POST, json);
            Logger.debug(String.format("URL:%s,Response JSON:%s", url, strResponse));
        } catch (Exception e) {
            Logger.error(url + " 发送json data 请求出错", e);
        }

        return strResponse;
    }
}
