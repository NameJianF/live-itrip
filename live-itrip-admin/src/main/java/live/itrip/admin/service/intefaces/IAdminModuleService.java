package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.AdminModule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/7/21.
 */
public interface IAdminModuleService {

    /**
     * 查询模块信息
     *
     * @param flag 是否标记删除的， 删除：1，正常：0
     * @return
     */
    List<AdminModule> selectModules(String flag);

    /**
     * @param decodeJson
     * @param response
     * @param request
     */
    void selectModules(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    /**
     * 查询模块详细信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    void selectModuleById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
