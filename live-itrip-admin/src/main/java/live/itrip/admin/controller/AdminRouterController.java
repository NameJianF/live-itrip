package live.itrip.admin.controller;

import live.itrip.admin.common.ViewConstants;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.model.*;
import live.itrip.admin.service.intefaces.*;
import org.apache.commons.lang3.StringUtils;
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
 * 后台管理 页面路由
 */
@Controller
public class AdminRouterController extends AbstractController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IAdminDictService iAdminDictService;
    @Autowired
    private IAdminDepartService iAdminDepartService;
    @Autowired
    private IAdminDictItemService iAdminDictItemService;
    @Autowired
    private IWebStaticInfoService iWebStaticInfoService;

    @RequestMapping(value = "/system/index", method = RequestMethod.GET)
    public String pagesIndex(HttpServletRequest request, Model model) {
        AdminUser user = iUserService.getCurrentLoginUser();
        model.addAttribute("user", user);
        return "pages/index";
    }

    @RequestMapping(value = "/system/login", method = RequestMethod.GET)
    public String pagesLogin() {
        return "pages/login";
    }

    @RequestMapping(value = "/system/profile", method = RequestMethod.GET)
    public String pagesProfile(HttpServletRequest request, Model model) {
        AdminUser user = iUserService.getCurrentLoginUser();
        model.addAttribute("user", user);
        return "pages/system/profile";
    }

    @RequestMapping(value = "/system/dashboard", method = RequestMethod.GET)
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

    @RequestMapping(value = "/system/apikey", method = RequestMethod.GET)
    public String systemApikey() {
        return "pages/system/apikey";
    }

    @RequestMapping(value = "/system/member", method = RequestMethod.GET)
    public String systemMember(HttpServletRequest request, Model model) {
        List<AdminDepart> departList = iAdminDepartService.selectAllDeparts();
        model.addAttribute("departList", departList);
        return "pages/system/member";
    }

    @RequestMapping(value = "/system/group", method = RequestMethod.GET)
    public String systemGroup(HttpServletRequest request, Model model) {
        List<AdminDepart> departList = iAdminDepartService.selectAllDeparts();
        model.addAttribute("departList", departList);
        return "pages/system/group";
    }

    /**
     * 系统日志
     *
     * @return
     */
    @RequestMapping(value = "/system/log", method = RequestMethod.GET)
    public String systemLog() {
        return "pages/system/log";
    }


    // ======================= web  view =============

    /**
     * 行程计划列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/view/product", method = RequestMethod.GET)
    public String viewProduct(HttpServletRequest request, Model model) {
        return "/pages/view/product";
    }

    /**
     * 行程计划:添加
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/view/editProduct", method = RequestMethod.GET)
    public String viewEditProduct(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        model.addAttribute("productId", id);


        // 加载数据
        List<AdminDictItem> itemList = iAdminDictItemService.selectAllDictItems();
        List<AdminDictItem> listType = new ArrayList<>();
        List<AdminDictItem> listDays = new ArrayList<>();
        List<AdminDictItem> listCity = new ArrayList<>();
        List<AdminDictItem> listTraffic = new ArrayList<>();
        for (AdminDictItem item : itemList) {
            if (item.getDictId().equals(5)) {
                // 线路类型
                listType.add(item);
            } else if (item.getDictId().equals(6)) {
                // 天数
                listDays.add(item);
            } else if (item.getDictId().equals(7)) {
                // 城市
                listCity.add(item);
            } else if (item.getDictId().equals(8)) {
                // 交通
                listTraffic.add(item);
            }
        }

        model.addAttribute("listType", listType);
        model.addAttribute("listDays", listDays);
        model.addAttribute("listCity", listCity);
        model.addAttribute("listTraffic", listTraffic);

        // 费用
        List<WebStaticInfo> infoList = iWebStaticInfoService.selectAllIdAndTitle();
        List<WebStaticInfo> listCosts = new ArrayList<>();
        List<WebStaticInfo> listReserves = new ArrayList<>();
        List<WebStaticInfo> listNotices = new ArrayList<>();
        if (infoList != null) {
            for (WebStaticInfo info : infoList) {
                if (ViewConstants.STATIC_TYPE_COST.equals(info.getType())) {
                    // 费用
                    listCosts.add(info);
                } else if (ViewConstants.STATIC_TYPE_RESERVE.equals(info.getType())) {
                    // 预定
                    listReserves.add(info);
                } else if (ViewConstants.STATIC_TYPE_NOTICE.equals(info.getType())) {
                    // 提醒
                    listNotices.add(info);
                }
            }
        }
        model.addAttribute("listCosts", listCosts);
        model.addAttribute("listReserves", listReserves);
        model.addAttribute("listNotices", listNotices);

        return "/pages/view/productNew";
    }

    /**
     * 行程计划:添加
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/view/newProduct", method = RequestMethod.GET)
    public String viewNewProduct(HttpServletRequest request, Model model) {
        // 加载数据
        List<AdminDictItem> itemList = iAdminDictItemService.selectAllDictItems();
        List<AdminDictItem> listType = new ArrayList<>();
        List<AdminDictItem> listDays = new ArrayList<>();
        List<AdminDictItem> listCity = new ArrayList<>();
        List<AdminDictItem> listTraffic = new ArrayList<>();
        for (AdminDictItem item : itemList) {
            if (item.getDictId().equals(5)) {
                // 线路类型
                listType.add(item);
            } else if (item.getDictId().equals(6)) {
                // 天数
                listDays.add(item);
            } else if (item.getDictId().equals(7)) {
                // 城市
                listCity.add(item);
            } else if (item.getDictId().equals(8)) {
                // 交通
                listTraffic.add(item);
            }
        }

        model.addAttribute("listType", listType);
        model.addAttribute("listDays", listDays);
        model.addAttribute("listCity", listCity);
        model.addAttribute("listTraffic", listTraffic);

        // 费用
        List<WebStaticInfo> infoList = iWebStaticInfoService.selectAllIdAndTitle();
        List<WebStaticInfo> listCosts = new ArrayList<>();
        List<WebStaticInfo> listReserves = new ArrayList<>();
        List<WebStaticInfo> listNotices = new ArrayList<>();
        if (infoList != null) {
            for (WebStaticInfo info : infoList) {
                if (ViewConstants.STATIC_TYPE_COST.equals(info.getType())) {
                    // 费用
                    listCosts.add(info);
                } else if (ViewConstants.STATIC_TYPE_RESERVE.equals(info.getType())) {
                    // 预定
                    listReserves.add(info);
                } else if (ViewConstants.STATIC_TYPE_NOTICE.equals(info.getType())) {
                    // 提醒
                    listNotices.add(info);
                }
            }
        }
        model.addAttribute("listCosts", listCosts);
        model.addAttribute("listReserves", listReserves);
        model.addAttribute("listNotices", listNotices);

        return "/pages/view/productNew";
    }


    /**
     * 客户定制
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/view/customer", method = RequestMethod.GET)
    public String viewCustomer(HttpServletRequest request, Model model) {
        return "/pages/view/customer";
    }

    /**
     * 旅行服务
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/view/tripService", method = RequestMethod.GET)
    public String viewTripService(HttpServletRequest request, Model model) {
        return "/pages/view/tripService";
    }

    /**
     * 在线反馈
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/view/onlineFaq", method = RequestMethod.GET)
    public String viewOnlineFaq(HttpServletRequest request, Model model) {
        return "/pages/view/faq";
    }

    /**
     * 固定内容设置页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/view/staticInfo", method = RequestMethod.GET)
    public String viewStaticInfo(HttpServletRequest request, Model model) {
        return "/pages/view/staticInfo";
    }

    /**
     * 城市风光 设置页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/system/view/citys", method = RequestMethod.GET)
    public String viewCitys(HttpServletRequest request, Model model) {
        return "/pages/view/citys";
    }
}
