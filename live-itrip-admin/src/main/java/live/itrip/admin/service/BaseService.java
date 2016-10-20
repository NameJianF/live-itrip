package live.itrip.admin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.PagerInfo;
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

    /**
     * 解析分页查询参数数据
     *
     * @param decodeJson
     * @return
     */
    protected PagerInfo getPagerInfo(String decodeJson) {

        JSONArray jsonarray = JSONArray.parseArray(decodeJson);
        PagerInfo pagerInfo = new PagerInfo();
        try {
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
                if (obj.get("name").equals("sEcho")) {
                    pagerInfo.setsEcho(obj.get("value").toString());
                } else if (obj.get("name").equals("iDisplayStart")) {
                    pagerInfo.setiDisplayStart(obj.getInteger("value"));
                } else if (obj.get("name").equals("iDisplayLength")) {
                    pagerInfo.setiDisplayLength(obj.getInteger("value"));
                } else if (obj.get("name").equals("draw")) {
                    pagerInfo.setDraw(obj.getInteger("value"));
                } else if (obj.get("name").equals("columns")) {
                    pagerInfo.setColumns(obj.getJSONArray("value"));
                } else if (obj.get("name").equals("order")) {
                    pagerInfo.setOrder(obj.getJSONArray("value"));
                } else if (obj.get("name").equals("start")) {
                    pagerInfo.setStart(obj.getInteger("value"));
                } else if (obj.get("name").equals("length")) {
                    pagerInfo.setLength(obj.getInteger("value"));
                } else if (obj.get("name").equals("search")) {
                    JSONObject jsonObject = obj.getJSONObject("value");
                    pagerInfo.setSearch(jsonObject.getString("value"));
                }
            }
        } catch (Exception ex) {
            Logger.error("", ex);
        }
        return pagerInfo;
    }
}
