package live.itrip.admin.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.dao.UserMapper;
import live.itrip.admin.model.User;
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
 * Created by Feng on 2016/7/5.
 * <p>
 * 用户操作相关
 */
@Controller
public class UserController extends AbstractController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;

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

        User user = this.userMapper.selectByUserName("admin");
        System.err.println(JSON.toJSON(user));

//        System.err.println("hello ---------");

        this.writeResponse(response,"hello ---------");
    }


    /**
     * 测试接口是否连通
     *
     * @param response
     * @param request
     */
    @RequestMapping("/user")
    public
    @ResponseBody
    void user(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
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
            if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                String op = header.getOp();
                if ("user.login".equalsIgnoreCase(op)) {
                    // login
                    iUserService.login(decodeJson, response, request);
                }
            }
        } catch (Exception ex) {
            Logger.error("", ex);
        }
    }
}
