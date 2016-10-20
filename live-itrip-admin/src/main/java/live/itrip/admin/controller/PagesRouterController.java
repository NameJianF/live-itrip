package live.itrip.admin.controller;

import live.itrip.admin.api.sso.bean.User;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.model.AdminDict;
import live.itrip.admin.service.intefaces.IAdminDictService;
import live.itrip.admin.service.intefaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Created by Feng on 2016/8/4.
 * <p>
 * action 页面导航/加载
 */
@Controller
public class PagesRouterController extends AbstractController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IAdminDictService iAdminDictService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String pagesIndex(HttpServletRequest request, Model model) {
        User user = iUserService.getCurrentLoginUser();
        model.addAttribute("user", user);
        return "pages/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String pagesLogin() {
        return "pages/login";
    }

    @RequestMapping(value = "/pages/dashboard", method = RequestMethod.GET)
    public String pagesDashboard() {
        return "pages/dashboard";
    }

    @RequestMapping(value = "/system/module", method = RequestMethod.GET)
    public String systemModule() {
        return "pages/system/module";
    }

    @RequestMapping(value = "/system/dictItem", method = RequestMethod.GET)
    public String dictItem(HttpServletRequest request, Model model) {
        List<AdminDict> dictList = iAdminDictService.selectAllDicts();
        model.addAttribute("dictList", dictList);

        return "pages/system/dictItem";
    }

    @RequestMapping(value = "/system/dict", method = RequestMethod.GET)
    public String systemDict() {
        return "pages/system/dict";
    }

    @RequestMapping(value = "/system/depart", method = RequestMethod.GET)
    public String systemDepart() {
        return "pages/system/depart";
    }

    @RequestMapping(value = "/system/operation", method = RequestMethod.GET)
    public String systemOperation() {
        return "pages/system/operation";
    }

    @RequestMapping(value = "/system/role", method = RequestMethod.GET)
    public String systemRole() {
        return "pages/system/role";
    }

    @RequestMapping(value = "/system/params", method = RequestMethod.GET)
    public String systemParams() {
        return "pages/system/params";
    }

    @RequestMapping(value = "/system/log", method = RequestMethod.GET)
    public String systemLog() {
        return "pages/system/log";
    }


}
