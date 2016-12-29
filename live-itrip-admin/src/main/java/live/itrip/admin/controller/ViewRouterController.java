package live.itrip.admin.controller;

import live.itrip.admin.common.HtmlUtils;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.model.AdminUser;
import live.itrip.admin.model.WebCityInfo;
import live.itrip.admin.model.WebProduct;
import live.itrip.admin.model.WebProductPlan;
import live.itrip.admin.service.intefaces.IUserService;
import live.itrip.admin.service.intefaces.IWebCityInfoService;
import live.itrip.admin.service.intefaces.IWebProductPlanService;
import live.itrip.admin.service.intefaces.IWebProductService;
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
import java.util.List;

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
    @Autowired
    private IWebProductService iWebProductService;
    @Autowired
    private IWebProductPlanService iWebProductPlanService;
    @Autowired
    private IUserService iUserService;


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
            response.sendRedirect("/cityError.html");
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
                response.sendRedirect("/cityError.html");
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
    public String viewProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        Integer pid = Integer.valueOf(request.getParameter("pid"));
        if (pid == null) {
            response.sendRedirect("/productError.html");
            return "";
        }

        WebProduct product = this.iWebProductService.selectProductById(pid);
        if (product == null) {
            response.sendRedirect("/productError.html");
            return "";
        }
        List<WebProductPlan> planList = iWebProductPlanService.selectPlanList(pid);

        model.addAttribute("product", product);
        model.addAttribute("planList", planList);

        return "/pages/view/template/prod";

    }


    /**
     * product detail by id
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/productToHtml", method = RequestMethod.GET)
    public void viewProductToHtml(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String rootPath = servletContext.getRealPath("");

        Integer pid = Integer.valueOf(request.getParameter("pid"));
        if (pid == null) {
            response.sendRedirect("/productError.html");
            return;
        }

        String url = "/view/product/" + pid + ".html";
        String htmlPath = rootPath + url;
        File file = new File(htmlPath);
        if (file.exists()) {
            // html 文件存在
            response.sendRedirect(url);
            return;
        }

        // html 文件不存在，生成html文件
        try {
            WebProduct product = this.iWebProductService.selectProductById(pid);
            if (product == null) {
                response.sendRedirect("/productError.html");
                return;
            }
            List<WebProductPlan> planList = iWebProductPlanService.selectPlanList(pid);

            servletContext.setAttribute("product", product);
            servletContext.setAttribute("planList", planList);

            String jspPath = "/pages/view/template/prod.jsp";
            HtmlUtils.createHtmlFile(servletContext, request, response, jspPath, htmlPath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.sendRedirect(url);
    }

    /**
     * user index
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/index", method = RequestMethod.GET)
    public String userIndex(HttpServletRequest request, Model model) throws IOException {
        AdminUser user = iUserService.getCurrentLoginUser();
        if (user == null) {
            return "redirect:/login.html";
        }
        model.addAttribute("user", user);
        return "view/user/index";
    }

    /**
     * user Home
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/home")
    public String userHome(HttpServletRequest request, Model model) throws IOException {
        AdminUser user = iUserService.getCurrentLoginUser();
        model.addAttribute("user", user);
        return "view/user/home";
    }

    /**
     * user orders
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/orders")
    public String userOrders(HttpServletRequest request, Model model) throws IOException {
        return "view/user/orders";
    }

    /**
     * user collect
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/collect")
    public String userCollect(HttpServletRequest request, Model model) throws IOException {
        return "view/user/collect";
    }

    /**
     * user blogs
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/blogs")
    public String userBlogs(HttpServletRequest request, Model model) throws IOException {


        return "view/user/blogs";
    }

    /**
     * user profile
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/profile")
    public String userProfile(HttpServletRequest request, Model model) throws IOException {


        return "view/user/profile";
    }
}
