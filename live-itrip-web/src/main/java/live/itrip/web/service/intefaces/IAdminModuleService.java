package live.itrip.web.service.intefaces;

import live.itrip.web.model.AdminModule;
import live.itrip.web.model.AdminUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
public interface IAdminModuleService {
    List<AdminModule> selectAllModules();

    List<AdminModule> selectModulesByUser(AdminUser user);

    void selectModules(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    /**
     * 查询模块详细信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void selectModuleById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    /**
     * 逻辑删除模块信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void deleteModuleById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    /**
     * 编辑模块信息：添加或修改
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void editModuleById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void modulePermissions(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
