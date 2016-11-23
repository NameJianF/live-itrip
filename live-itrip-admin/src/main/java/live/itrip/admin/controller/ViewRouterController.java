package live.itrip.admin.controller;

import live.itrip.admin.common.HtmlUtils;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.model.WebCityInfo;
import live.itrip.admin.service.intefaces.IWebCityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;

/**
 * Created by Feng on 2016/8/4.
 * <p>
 * 前端 页面路由
 */
@Controller
public class ViewRouterController extends AbstractController {
    @Autowired
    private ServletContext servletContext;

    @Autowired
    private IWebCityInfoService iWebCityInfoService;

    /**
     * city by id
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/city", method = RequestMethod.GET)
    public void viewCity(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String rootPath = servletContext.getRealPath("");

        Integer id = Integer.valueOf(request.getParameter("id"));
        if (id == null) {
            response.sendRedirect("/cityerror.html");
            return;
        }

        String url = "/view/city/" + id + ".html";
        String htmlPath = rootPath + url;
        File file = new File(htmlPath);
        if (file.exists()) {
            // html 文件存在
            response.sendRedirect(url);
            return;
        }

        // html 文件不存在，生成html文件
        try {

            WebCityInfo city = iWebCityInfoService.selectCityInfoById(id);
            if (city == null) {
                response.sendRedirect("/cityerror.html");
                return;
            }
            servletContext.setAttribute("id", city.getId());
            servletContext.setAttribute("cityName", city.getCityName());
            servletContext.setAttribute("cityContent", city.getCityContent());

            String jspPath = "/pages/view/template/city.jsp";
            HtmlUtils.createHtmlFile(servletContext, request, response, jspPath, htmlPath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.sendRedirect(url);
    }

    /**
     * product detail by id
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/product", method = RequestMethod.GET)
    public void viewProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        Integer pid = Integer.valueOf(request.getParameter("pid"));
        if (pid.equals(1000)) {
            response.sendRedirect("/view/products/1000.html");
        }
    }
}
