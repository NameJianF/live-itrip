package live.itrip.sso.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.request.RequestHeader;
import live.itrip.common.response.BaseResult;
import live.itrip.common.util.JsonStringUtils;
import live.itrip.sso.common.UserOprations;
import live.itrip.sso.service.interfaces.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * webapi op 导航
 * 只提供接口调用，api管理平台不使用
 */
@Controller
public class UserController extends AbstractController {

    @Autowired
    private IUserService iUserService;

    /**
     * 测试接口是否连通
     *
     * @param response
     * @param request
     */
    @RequestMapping("/test")
    public
    @ResponseBody
    void test(HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg("hello api is running.");
        this.writeResponse(response, result);
    }

    /**
     * 用户相关操作
     *
     * @param json
     * @param response
     * @param request
     */
    @RequestMapping("/user")
    public
    @ResponseBody
    void user(@RequestBody String json,
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
            BaseResult error = this.validateParams(header, decodeJson);
            if (error.getCode() == ErrorCode.SUCCESS.getCode()) {
                if (UserOprations.OP_API_USER_REGISTER.equalsIgnoreCase(op)
                        || UserOprations.OP_API_USER_RETRIEVE_PWD.equalsIgnoreCase(op)) {

                    if (UserOprations.OP_API_USER_REGISTER.equalsIgnoreCase(op)) {
                        // 注册
                        iUserService.register(decodeJson, response, request);
                    } else if (UserOprations.OP_API_USER_RETRIEVE_PWD.equalsIgnoreCase(op)) {
                        // 找回密码
                        iUserService.retrievePassword(decodeJson, response, request);
                    } else if (UserOprations.OP_API_USER_UPDATE_INFO.equalsIgnoreCase(op)) {
                        // 用户信息修改
                        iUserService.updateUserInfo(decodeJson, response, request);
                    } else if (UserOprations.OP_API_USER_UPDATE_PWD.equalsIgnoreCase(op)) {
                        // 修改用户密码
                        iUserService.updatePassword(decodeJson, response, request);
                    }
                }
            } else {
                this.writeResponseErrorApp(response, error);
            }
        } catch (Exception ex) {
        }
    }


}
