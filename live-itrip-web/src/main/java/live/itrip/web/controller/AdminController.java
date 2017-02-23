package live.itrip.web.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.web.controller.base.AbstractController;
import live.itrip.web.service.intefaces.*;
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
 * Created by Feng on 2016/8/5.
 */
@Controller
public class AdminController extends AbstractController {

    @Autowired
    private IAdminModuleService iAdminModuleService;
    @Autowired
    private IAdminDictService iAdminDictService;
    @Autowired
    private IAdminDictItemService iAdminDictItemService;
    @Autowired
    private IAdminDepartService iAdminDepartService;
    @Autowired
    private IAdminGroupService iAdminGroupService;
    @Autowired
    private IAdminOperationService iAdminOperationService;
    @Autowired
    private IAdminRoleService iAdminRoleService;
    @Autowired
    private IAdminUserService iAdminUserService;
    @Autowired
    private IWebProductService iWebProductService;
    @Autowired
    private IWebStaticInfoService iWebStaticInfoService;
    @Autowired
    private IWebProductPlanService iWebProductPlanService;
    @Autowired
    private IWebServiceOrderService iWebServiceOrderService;
    @Autowired
    private IWebCustomerAskService iWebCustomerAskService;
    @Autowired
    private IWebFaqService iWebFaqService;
    @Autowired
    private IWebCityInfoService webCityInfoService;
    @Autowired
    private IAdminRolePermissionService iAdminRolePermissionService;

    // =================== system config ==============

    /**
     * 系统配置模块
     *
     * @param json
     * @param response
     * @param request
     */
    @RequestMapping("/sysCfg")
    public
    @ResponseBody
    void systemConfig(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "systemConfig", decodeJson));
        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        String flag = request.getParameter("flag");
        // 1: from table select

        if (StringUtils.isNotEmpty(flag)) {
            if ("module".equalsIgnoreCase(flag)) {
                // 查询模块信息： op = module.select
                iAdminModuleService.selectModules(decodeJson, response, request);
            } else if ("dict".equalsIgnoreCase(flag)) {
                iAdminDictService.selectDicts(decodeJson, response, request);
            } else if ("dictItem".equalsIgnoreCase(flag)) {
                iAdminDictItemService.selectDictItems(decodeJson, response, request);
            } else if ("depart".equalsIgnoreCase(flag)) {
                iAdminDepartService.selectDeparts(decodeJson, response, request);
            } else if ("group".equalsIgnoreCase(flag)) {
                iAdminGroupService.selectGroups(decodeJson, response, request);
            } else if ("operation".equalsIgnoreCase(flag)) {
                iAdminOperationService.selectOperations(decodeJson, response, request);
            } else if ("role".equalsIgnoreCase(flag)) {
                iAdminRoleService.selectRoles(decodeJson, response, request);
            } else if ("member".equalsIgnoreCase(flag)) {
                iAdminUserService.selectAdminUsers(decodeJson, response, request);
            } else if ("modulePermissions".equalsIgnoreCase(flag)) {
                iAdminModuleService.modulePermissions(decodeJson, response, request);
            }
        } else {
            try {
                RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
                if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                    String op = header.getOp();
                    // module
                    if ("module.detail".equalsIgnoreCase(op)) {
                        iAdminModuleService.selectModuleById(decodeJson, response, request);
                    } else if ("module.delete".equalsIgnoreCase(op)) {
                        iAdminModuleService.deleteModuleById(decodeJson, response, request);
                    } else if ("module.edit".equalsIgnoreCase(op)) {
                        iAdminModuleService.editModuleById(decodeJson, response, request);
                    }
                    // dict
                    else if ("dict.detail".equalsIgnoreCase(op)) {
                        iAdminDictService.selectDictById(decodeJson, response, request);
                    } else if ("dict.delete".equalsIgnoreCase(op)) {
                        iAdminDictService.deleteDictById(decodeJson, response, request);
                    } else if ("dict.edit".equalsIgnoreCase(op)) {
                        iAdminDictService.editDictById(decodeJson, response, request);
                    }
                    // dict item
                    else if ("dictItem.detail".equalsIgnoreCase(op)) {
                        iAdminDictItemService.selectDictItemById(decodeJson, response, request);
                    } else if ("dictItem.delete".equalsIgnoreCase(op)) {
                        iAdminDictItemService.deleteDictItemById(decodeJson, response, request);
                    } else if ("dictItem.edit".equalsIgnoreCase(op)) {
                        iAdminDictItemService.editDictItemById(decodeJson, response, request);
                    }
                    // depart
                    else if ("depart.detail".equalsIgnoreCase(op)) {
                        iAdminDepartService.selectDepartById(decodeJson, response, request);
                    } else if ("depart.delete".equalsIgnoreCase(op)) {
                        iAdminDepartService.deleteDepartById(decodeJson, response, request);
                    } else if ("depart.edit".equalsIgnoreCase(op)) {
                        iAdminDepartService.editDepartById(decodeJson, response, request);
                    }
                    // group
                    else if ("group.detail".equalsIgnoreCase(op)) {
                        iAdminGroupService.selectGroupById(decodeJson, response, request);
                    } else if ("group.delete".equalsIgnoreCase(op)) {
                        iAdminGroupService.deleteGroupById(decodeJson, response, request);
                    } else if ("group.edit".equalsIgnoreCase(op)) {
                        iAdminGroupService.editGroupById(decodeJson, response, request);
                    } else if ("group.selectGroupsByDepartId".equalsIgnoreCase(op)) {
                        iAdminGroupService.selectGroupsByDepartId(decodeJson, response, request);
                    }
                    // operation
                    else if ("operation.detail".equalsIgnoreCase(op)) {
                        iAdminOperationService.selectOperationById(decodeJson, response, request);
                    } else if ("operation.delete".equalsIgnoreCase(op)) {
                        iAdminOperationService.deleteOperationById(decodeJson, response, request);
                    } else if ("operation.edit".equalsIgnoreCase(op)) {
                        iAdminOperationService.editOperationById(decodeJson, response, request);
                    }
                    // role
                    else if ("role.detail".equalsIgnoreCase(op)) {
                        iAdminRoleService.selectRoleById(decodeJson, response, request);
                    } else if ("role.delete".equalsIgnoreCase(op)) {
                        iAdminRoleService.deleteRoleById(decodeJson, response, request);
                    } else if ("role.edit".equalsIgnoreCase(op)) {
                        iAdminRoleService.editRoleById(decodeJson, response, request);
                    }
                    // member
                    else if ("member.detail".equalsIgnoreCase(op)) {
                        iAdminUserService.selectAdminUserById(decodeJson, response, request);
                    } else if ("member.delete".equalsIgnoreCase(op)) {
                        iAdminUserService.deleteAdminUserById(decodeJson, response, request);
                    } else if ("member.edit".equalsIgnoreCase(op)) {
                        iAdminUserService.editAdminUserById(decodeJson, response, request);
                    }
                    // RolePermission
                    else if ("rolePermission.detail".equalsIgnoreCase(op)) {
                        iAdminRolePermissionService.selectPermissionsByRoleId(decodeJson, response, request);
                    }else if ("rolePermission.edit".equalsIgnoreCase(op)) {
                        iAdminRolePermissionService.modifyPermissionsByRoleId(decodeJson, response, request);
                    }
                }
            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }


    // =================== websit ==============

    /**
     * 网站后台，行程详情查询
     *
     * @param response
     * @param request
     */
    @RequestMapping("/system/view/planDetail")
    public
    @ResponseBody
    void viewPlanDetail(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "viewPlanDetail", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        String flag = request.getParameter("flag");

        if (StringUtils.isNotEmpty(flag)) {
            // from table select
            iWebProductPlanService.selectPlanDetailsByProductId(decodeJson, response, request);
        } else {
            try {
                RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
                if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                    String op = header.getOp();
                    // product
                    if ("planDetail.detail".equalsIgnoreCase(op)) {
                        iWebProductPlanService.selectPlanById(decodeJson, response, request);
                    } else if ("planDetail.edit".equalsIgnoreCase(op)) {
                        iWebProductPlanService.editPlanById(decodeJson, response, request);
                    } else if ("planDetail.delete".equalsIgnoreCase(op)) {
                        iWebProductPlanService.deletePlanById(decodeJson, response, request);
                    } else if ("planDetail.preList".equalsIgnoreCase(op)) {
                        iWebProductPlanService.selectPlanList(decodeJson, response, request);
                    }
                }

            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }

    /**
     * 网站后台，行程列表查询
     *
     * @param response
     * @param request
     */
    @RequestMapping("/system/view/product")
    public
    @ResponseBody
    void viewProduct(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "viewProduct", decodeJson));

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
     * 网站后台，静态信息
     *
     * @param response
     * @param request
     */
    @RequestMapping("/system/view/staticInfo")
    public
    @ResponseBody
    void viewStaticInfo(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "viewStaticInfo", decodeJson));

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

    /**
     * 网站后台，旅行服务
     *
     * @param response
     * @param request
     */
    @RequestMapping("/system/view/tripService")
    public
    @ResponseBody
    void tripService(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "tripService", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        String flag = request.getParameter("flag");

        if (StringUtils.isNotEmpty(flag)) {
            // from table select
            iWebServiceOrderService.selectOrderList(decodeJson, response, request);
        } else {
            try {
                RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
                if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                    String op = header.getOp();
                    // tripService
                    if ("tripService.detail".equalsIgnoreCase(op)) {
                        iWebServiceOrderService.selectOrderInfoById(decodeJson, response, request);
                    } else if ("tripService.edit".equalsIgnoreCase(op)) {
                        iWebServiceOrderService.editOrderInfoById(decodeJson, response, request);
                    } else if ("tripService.delete".equalsIgnoreCase(op)) {
                        iWebServiceOrderService.deleteOrderInfoById(decodeJson, response, request);
                    }
                }

            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }

    /**
     * 网站后台，客户定制
     *
     * @param response
     * @param request
     */
    @RequestMapping("/system/view/customer")
    public
    @ResponseBody
    void customer(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "customer", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        String flag = request.getParameter("flag");

        if (StringUtils.isNotEmpty(flag)) {
            // from table select
            iWebCustomerAskService.selectCustomerAskList(decodeJson, response, request);
        } else {
            try {
                RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
                if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                    String op = header.getOp();
                    // customer
                    if ("customer.detail".equalsIgnoreCase(op)) {
                        iWebCustomerAskService.selectAskInfoById(decodeJson, response, request);
                    } else if ("customer.edit".equalsIgnoreCase(op)) {
                        iWebCustomerAskService.editAskInfoById(decodeJson, response, request);
                    } else if ("customer.delete".equalsIgnoreCase(op)) {
                        iWebCustomerAskService.deleteAskInfoById(decodeJson, response, request);
                    }
                }

            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }

    /**
     * 网站后台，客户反馈
     *
     * @param response
     * @param request
     */
    @RequestMapping("/system/view/faq")
    public
    @ResponseBody
    void faq(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "faq", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        String flag = request.getParameter("flag");

        if (StringUtils.isNotEmpty(flag)) {
            // from table select
            iWebFaqService.selectFaqList(decodeJson, response, request);
        } else {
            try {
                RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
                if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                    String op = header.getOp();
                    // customer
                    if ("faq.detail".equalsIgnoreCase(op)) {
                        iWebFaqService.selectFaqInfoById(decodeJson, response, request);
                    } else if ("faq.edit".equalsIgnoreCase(op)) {
                        iWebFaqService.editFaqInfoById(decodeJson, response, request);
                    } else if ("faq.delete".equalsIgnoreCase(op)) {
                        iWebFaqService.deleteFaqInfoById(decodeJson, response, request);
                    }
                }

            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }

    /**
     * 网站后台，客户反馈
     *
     * @param response
     * @param request
     */
    @RequestMapping("/system/view/citys")
    public
    @ResponseBody
    void citys(@RequestBody String json, HttpServletResponse response, HttpServletRequest request) {
        String decodeJson = JsonStringUtils.decoderForJsonString(json);
        Logger.debug(
                String.format("timestamp:%s action:%s json:%s",
                        System.currentTimeMillis(), "citys", decodeJson));

        if (StringUtils.isEmpty(decodeJson)) {
            this.paramInvalid(response, "JSON");
            return;
        }
        String flag = request.getParameter("flag");

        if (StringUtils.isNotEmpty(flag)) {
            // from table select
            webCityInfoService.selectCityList(decodeJson, response, request);
        } else {
            try {
                RequestHeader header = JSON.parseObject(decodeJson, RequestHeader.class);
                if (header != null && StringUtils.isNotEmpty(header.getOp())) {
                    String op = header.getOp();
                    // city
                    if ("citys.detail".equalsIgnoreCase(op)) {
                        webCityInfoService.selectCityInfoById(decodeJson, response, request);
                    } else if ("citys.edit".equalsIgnoreCase(op)) {
                        webCityInfoService.editCityInfoById(decodeJson, response, request);
                    } else if ("citys.delete".equalsIgnoreCase(op)) {
                        webCityInfoService.deleteCityInfoById(decodeJson, response, request);
                    }
                }

            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }
}
