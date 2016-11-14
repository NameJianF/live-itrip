package live.itrip.admin.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.service.intefaces.IWebProductService;
import live.itrip.admin.service.intefaces.IWebStaticInfoService;
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
    private IWebProductService iWebProductService;
    @Autowired
    private IWebStaticInfoService iWebStaticInfoService;


    /**
     * 完整后台，行程列表查询
     *
     * @param response
     * @param request
     */
    @RequestMapping("/view/product")
    public
    @ResponseBody
    void viewProduct(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "user", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        String flag = request.getParameter("flag");

        if (StringUtils.isNotEmpty(flag)) {
            // from table select
            iWebProductService.selectProductList(decodeJson, response, request);
        } else {
            try {
                RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
                if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                    String op = header.getOp();
                    // product
                    if ("product.detail".equalsIgnoreCase(op)) {
                        iWebProductService.selectProductById(decodeJson, response, request);
                    } else if ("product.edit".equalsIgnoreCase(op)) {
                        iWebProductService.editProductById(decodeJson, response, request);
                    } else if ("product.delete".equalsIgnoreCase(op)) {
                        iWebProductService.deleteProductById(decodeJson, response, request);
                    }
                }

            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }

    /**
     * 完整后台，静态信息
     *
     * @param response
     * @param request
     */
    @RequestMapping("/view/staticInfo")
    public
    @ResponseBody
    void viewStaticInfo(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "user", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        String flag = request.getParameter("flag");

        if (StringUtils.isNotEmpty(flag)) {
            // from table select
            iWebStaticInfoService.selectStaticInfoList(decodeJson, response, request);
        } else {
            try {
                RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
                if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                    String op = header.getOp();
                    // staticInfo
                    if ("staticInfo.detail".equalsIgnoreCase(op)) {
                        iWebStaticInfoService.selectStaticInfoById(decodeJson, response, request);
                    } else if ("staticInfo.edit".equalsIgnoreCase(op)) {
                        iWebStaticInfoService.editStaticInfoById(decodeJson, response, request);
                    } else if ("staticInfo.delete".equalsIgnoreCase(op)) {
                        iWebStaticInfoService.deleteStaticInfoById(decodeJson, response, request);
                    }
                }

            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }

}
