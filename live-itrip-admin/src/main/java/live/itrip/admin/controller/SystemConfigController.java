package live.itrip.admin.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.service.intefaces.*;
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
public class SystemConfigController extends AbstractController {

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
    private IClientApiKeyService iClientApiKeyService;
    @Autowired
    private IAdminUserService iAdminUserService;

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
                        System.currentTimeMillis(), "user", decodeJson));
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
            } else if ("apikey".equalsIgnoreCase(flag)) {
                iClientApiKeyService.selectApikeys(decodeJson, response, request);
            } else if ("member".equalsIgnoreCase(flag)) {
                iAdminUserService.selectAdminUsers(decodeJson, response, request);
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
                    // apikey
                    else if ("apikey.detail".equalsIgnoreCase(op)) {
                        iClientApiKeyService.selectApikeyById(decodeJson, response, request);
                    } else if ("apikey.delete".equalsIgnoreCase(op)) {
                        iClientApiKeyService.deleteApikeyById(decodeJson, response, request);
                    } else if ("apikey.edit".equalsIgnoreCase(op)) {
                        iClientApiKeyService.editApikeyById(decodeJson, response, request);
                    }
                    // member
                    else if ("member.detail".equalsIgnoreCase(op)) {
                        iAdminUserService.selectAdminUserById(decodeJson, response, request);
                    } else if ("member.delete".equalsIgnoreCase(op)) {
                        iAdminUserService.deleteAdminUserById(decodeJson, response, request);
                    } else if ("member.edit".equalsIgnoreCase(op)) {
                        iAdminUserService.editAdminUserById(decodeJson, response, request);
                    }
                }
            } catch (Exception ex) {
                Logger.error("", ex);
            }
        }
    }


}
