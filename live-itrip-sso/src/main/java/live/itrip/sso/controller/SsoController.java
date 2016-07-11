package live.itrip.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import live.itrip.common.Logger;
import live.itrip.common.request.RequestHeader;
import live.itrip.common.response.BaseResult;
import live.itrip.common.util.JsonStringUtils;
import live.itrip.sso.common.SsoOprations;
import live.itrip.sso.service.interfaces.ISsoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class SsoController extends BaseController {

    @Autowired
    private ISsoService iSsoService;

    /**
     * sso 用户相关操作
     *
     * @param json
     * @param response
     * @param request
     */
    @RequestMapping("/sso")
    public
    @ResponseBody
    void sso(@RequestBody String json,
             HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.URLDecoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "user", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.jsonIsEmpty(response);
            return;
        }

        try {
            RequestHeader header = JSON
                    .parseObject(decodeJson, RequestHeader.class);

            String op = header.getOp();

            // validate Api Params
            BaseResult error = this.validateParams(header);
            if (error == null) {
                if (SsoOprations.OP_SSO_LOGIN.equalsIgnoreCase(op)) {
                    // login
                    iSsoService.login(decodeJson, response, request);
                } else if (SsoOprations.OP_SSO_LOGOUT.equalsIgnoreCase(op)) {
                    // logout
                } else if (SsoOprations.OP_SSO_AUTH.equalsIgnoreCase(op)) {
                    // auth
                    iSsoService.authUser(decodeJson, response, request);
                }
            } else {
                this.writeResponseErrorApp(response, error);
            }

        } catch (Exception ex) {
        }
    }
}
