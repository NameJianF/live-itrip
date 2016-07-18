package live.itrip.admin.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.cache.RedisCacheClient;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.model.ClientApiKey;
import live.itrip.admin.service.intefaces.IClientApiKeyService;
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
 * Created by Feng on 2016/7/14.
 */
@Controller
public class ApiKeyController extends AbstractController {
    @Autowired
    private IClientApiKeyService iClientApiKeyService;

    /**
     * @param response
     * @param request
     */
    @RequestMapping("/apikeys")
    public
    @ResponseBody
    void apikeys(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.URLDecoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "user", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        try {
            RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
            // 校验apikey
            ClientApiKey clientApiKey = this.validateClientApiKey(header.getApikey());
            if (clientApiKey == null) {
                this.paramInvalid(response, "apikey");
                return;
            }
            // 校验sig
            if (!this.validateSig(clientApiKey, header)) {
                this.paramInvalid(response, "sig");
                return;
            }

            // 校验 timestamp
            if (!this.validateTimestamp(header.getTimestamp())) {
                this.paramInvalid(response, "timestamp");
                return;
            }

            // 校验 token
            if (!this.validateToken(header.getSid())) {
                this.paramInvalid(response, "token");
                return;
            }


            // dispatch op
            if (StringUtils.isNotEmpty(header.getOp())) {
                String op = header.getOp();
                if ("ApiKey.list".equalsIgnoreCase(op)) {
                    // login
                    iClientApiKeyService.selectKeyList(decodeJson, response, request);
                }
            }
        } catch (Exception ex) {
            Logger.error("", ex);
        }
    }

}
