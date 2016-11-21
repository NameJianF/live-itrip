package live.itrip.admin.controller;

import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.service.intefaces.IWebCustomerAskService;
import live.itrip.admin.service.intefaces.IWebServiceOrderService;
import live.itrip.common.Logger;
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
public class ViewPagesController extends AbstractController {
    @Autowired
    private IWebCustomerAskService iWebCustomerAskService;
    @Autowired
    private IWebServiceOrderService iWebServiceOrderService;


    /**
     * @param response
     * @param request
     */
    @RequestMapping("/view/customerAsk")
    public
    @ResponseBody
    void customerAsk(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "user", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        try {
            // 记录客户定制询问信息
            iWebCustomerAskService.insertCustomerAsk(decodeJson, response, request);
        } catch (Exception ex) {
            Logger.error("", ex);
        }
    }

    /**
     * @param response
     * @param request
     */
    @RequestMapping("/view/serviceOrder")
    public
    @ResponseBody
    void serviceOrder(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "user", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        try {
            // 记录客户定制服务信息
            iWebServiceOrderService.insertServiceOrder(decodeJson, response, request);
        } catch (Exception ex) {
            Logger.error("", ex);
        }
    }

}
