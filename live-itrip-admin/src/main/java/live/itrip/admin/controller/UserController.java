package live.itrip.admin.controller;

import live.itrip.admin.controller.base.AbstractController;
import org.springframework.stereotype.Controller;
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
@RequestMapping("user")
public class UserController extends AbstractController {

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

//        JedisClientSingleImpl jedisClientSingle = new JedisClientSingleImpl();
//        jedisClientSingle.set("feng", "feng-vale");
//        String value = jedisClientSingle.get("feng");
//        System.err.println("-------------: " + value);
//
//        System.err.println("-------------: " + jedisClientSingle.dbSize());

    }
}
