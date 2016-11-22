package live.itrip.admin.controller;

import live.itrip.admin.common.ViewConstants;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.model.*;
import live.itrip.admin.service.intefaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feng on 2016/8/4.
 * <p>
 * 前端 页面路由
 */
@Controller
public class ViewRouterController extends AbstractController {

    /**
     * product detail by id
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/product", method = RequestMethod.GET)
    public void viewGetProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        Integer pid = Integer.valueOf(request.getParameter("pid"));
        if (pid.equals(1000)) {
            response.sendRedirect("/view/products/1000.html");
//            return "";
        }
//        return "/pages/view/product";
    }
}
