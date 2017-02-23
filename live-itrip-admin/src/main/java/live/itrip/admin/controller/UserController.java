package live.itrip.admin.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.service.intefaces.IUserService;
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
 * Created by Feng on 2016/10/12.
 * <p>
 * 用户相关，不需要登录验证的action
 */
@Controller
public class UserController extends AbstractController {
    @Autowired
    private IUserService iUserService;


    /**
     * @param response
     * @param request
     */
    @RequestMapping("/user")
    public
    @ResponseBody
    void user(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "user", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        try {
            RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
            // dispatch op
            if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                String op = header.getOp();
                if ("user.login".equalsIgnoreCase(op)) {
                    // login
                    iUserService.userLogin(decodeJson, response, request);
                } else if ("user.logout".equalsIgnoreCase(op)) {
                    // logout
                    iUserService.userLogout(decodeJson, response, request);
                }
            }
        } catch (Exception ex) {
            Logger.error("", ex);
        }
    }


}
