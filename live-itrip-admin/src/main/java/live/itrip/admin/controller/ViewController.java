package live.itrip.admin.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.service.impls.UserService;
import live.itrip.admin.service.intefaces.IWebCustomerAskService;
import live.itrip.admin.service.intefaces.IWebProductService;
import live.itrip.admin.service.intefaces.IWebServiceOrderService;
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
 * Created by Feng on 2016/8/4.
 * <p>
 * websit 网站页面
 * action 页面导航/加载
 */
@Controller
public class ViewController extends AbstractController {

    @Autowired
    private IWebCustomerAskService iWebCustomerAskService;
    @Autowired
    private IWebServiceOrderService iWebServiceOrderService;
    @Autowired
    private IWebProductService iWebProductService;
    @Autowired
    private UserService userService;

    /**
     * 前台用户登录
     *
     * @param response
     * @param request
     */
    @RequestMapping("/view/login")
    public
    @ResponseBody
    void login(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "user", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }

        userService.userLogin(decodeJson, response, request);
    }

    /**
     * 前台用户退出
     *
     * @param response
     * @param request
     */
    @RequestMapping("/view/logout")
    public
    @ResponseBody
    void logout(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {

    }

    /**
     * 前台用户注册
     *
     * @param response
     * @param request
     */
    @RequestMapping("/view/register")
    public
    @ResponseBody
    void register(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {

    }

    /**
     * @param response
     * @param request
     */
    @RequestMapping("/view/customerAsk")
    public
    @ResponseBody
    void customerAsk(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(String.format("timestamp:%s action:%s json:%s", System.currentTimeMillis(), "customerAsk", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        try {
            RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
            if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                String op = header.getOp();
                // customerAsk
                if ("customerAsk.add".equalsIgnoreCase(op)) {
                    // 记录客户定制询问信息
                    iWebCustomerAskService.insertCustomerAsk(decodeJson, response, request);
                }
//                else if ("customerAsk.edit".equalsIgnoreCase(op)) {
//
//                } else if ("customerAsk.delete".equalsIgnoreCase(op)) {
//
//                }
            }


        } catch (Exception ex) {
            Logger.error("", ex);
        }
    }

    /**
     * @param response
     * @param request
     */
    @RequestMapping("/view/tripService")
    public
    @ResponseBody
    void tripService(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(String.format("timestamp:%s action:%s json:%s", System.currentTimeMillis(), "tripService", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        try {
            RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
            if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                String op = header.getOp();
                // tripService
                if ("tripService.add".equalsIgnoreCase(op)) {
                    // 记录客户定制服务信息
                    iWebServiceOrderService.insertServiceOrder(decodeJson, response, request);
                }
            }
        } catch (Exception ex) {
            Logger.error("", ex);
        }
    }

    /**
     * 页面  产品相关action
     *
     * @param response
     * @param request
     */
    @RequestMapping("/view/product")
    public
    @ResponseBody
    void product(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(String.format("timestamp:%s action:%s json:%s", System.currentTimeMillis(), "product", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        try {
            RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
            if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                String op = header.getOp();
                // product
                if ("product.selectByCity".equalsIgnoreCase(op)) {
                    // 根据 cicy id 查询相关 product
                    iWebProductService.selectHotProductsByCityId(decodeJson, response, request);
                } else if ("product.selectAbouts".equalsIgnoreCase(op)) {
                    // 查询 某产品相关的产品
                    iWebProductService.selectProductListAbouts(decodeJson, response, request);
                } else if ("product.selectProductList".equalsIgnoreCase(op)) {
                    // 查询分类产品
                    iWebProductService.selectProductListByType(decodeJson, response, request);
                }
            }
        } catch (Exception ex) {
            Logger.error("", ex);
        }
    }

}
