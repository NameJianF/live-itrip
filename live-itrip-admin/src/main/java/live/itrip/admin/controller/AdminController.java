package live.itrip.admin.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.service.intefaces.*;
import live.itrip.common.Logger;
import live.itrip.common.request.RequestHeader;
import live.itrip.common.util.JsonStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/8/5.
 */
@Controller
public class AdminController extends AbstractController {

    @Autowired
    private IClientApiKeyService iClientApiKeyService;

    // =================== system config ==============

    /**
     * 系统配置模块
     *
     * @param json
     * @param response
     * @param request
     */
    @RequestMapping("/sysCfg")
    public
    @ResponseBody
    void systemConfig(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "systemConfig", decodeJson));
        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        String flag = request.getParameter("flag");
        // 1: from table select

        if (StringUtils.isNotEmpty(flag)) {
            if ("apikey".equalsIgnoreCase(flag)) {
                iClientApiKeyService.selectApikeys(decodeJson, response, request);
            }
        } else {
            try {
                RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
                if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                    String op = header.getOp();

                    // apikey
                    if ("apikey.detail".equalsIgnoreCase(op)) {
                        iClientApiKeyService.selectApikeyById(decodeJson, response, request);
                    } else if ("apikey.delete".equalsIgnoreCase(op)) {
                        iClientApiKeyService.deleteApikeyById(decodeJson, response, request);
                    } else if ("apikey.edit".equalsIgnoreCase(op)) {
                        iClientApiKeyService.editApikeyById(decodeJson, response, request);
                    }
                }
            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }
}
