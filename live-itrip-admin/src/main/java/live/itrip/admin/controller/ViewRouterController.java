package live.itrip.admin.controller;

import live.itrip.admin.controller.base.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Feng on 2016/8/4.
 * <p>
 * websit 网站页面
 * action 页面导航/加载
 */
@Controller
public class ViewRouterController extends AbstractController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String viewIndex(HttpServletRequest request, Model model) {
        return "/index.html";
    }

}
