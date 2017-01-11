package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.AdminModuleMapper;
import live.itrip.admin.model.AdminModule;
import live.itrip.admin.model.AdminUserPermission;
import live.itrip.admin.model.User;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminModuleService;
import live.itrip.admin.service.intefaces.IAdminUserPermissionService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
@Service
public class AdminModuleService extends BaseService implements IAdminModuleService {
    @Autowired
    private AdminModuleMapper adminModuleMapper;

    @Autowired
    private IAdminUserPermissionService iAdminUserPermissionService;


    /**
     * 查询全部未标记删除的数据
     *
     * @return
     */
    @Override
    public List<AdminModule> selectAllModules() {
        return adminModuleMapper.selectAllModules("0");
    }

    /**
     * 根据用户权限设置查询数据
     *
     * @param user
     * @return
     */
    @Override
    public List<AdminModule> selectModulesByUser(User user) {
        List<Integer> listModuleId = new ArrayList<Integer>();
        List<AdminUserPermission> permissions = iAdminUserPermissionService.selectUserPermissionsByUserId(user.getId());
        for (AdminUserPermission adminUserPermission : permissions) {
            listModuleId.add(adminUserPermission.getModuleId());
        }
        return adminModuleMapper.selectModulesByIds(listModuleId);
    }

    /**
     * 查询全部数据
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectModules(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<AdminModule> result = new BootStrapDataTableList<AdminModule>();
        try {
            // 解析查询条件
            JSONArray jsonarray = JSONArray.parseArray(decodeJson);
            String queryContent = null;
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
                if (obj.get("name").equals("queryContent")) {
                    queryContent = obj.get("value").toString();
                }
            }
            if (StringUtils.isNotEmpty(queryContent)) {
                queryContent = "'%" + queryContent.trim() + "%'";
            }

            PagerInfo pagerInfo = this.getPagerInfo(jsonarray);
            Integer count = adminModuleMapper.countAll();
            List<AdminModule> moduleList = adminModuleMapper.selectModules(queryContent,pagerInfo.getStart(), pagerInfo.getLength());
            if (moduleList != null) {
                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(count);
                result.setiTotalDisplayRecords(count);
                result.setAaData(moduleList);

                // response
                this.writeResponse(response, result);
                return;
            }
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }

        BaseResult error = new BaseResult();
        error.setCode(ErrorCode.UNKNOWN.getCode());
        this.writeResponse(response, error);
    }

    /**
     * 查询模块详细信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectModuleById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer moduleid = (Integer) jsonObject.get("moduleid");
        if (moduleid != null) {
            AdminModule adminModule = this.adminModuleMapper.selectByPrimaryKey(moduleid);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(adminModule);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    /**
     * 逻辑删除模块信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void deleteModuleById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer moduleid = (Integer) jsonObject.get("moduleid");
        if (moduleid != null) {
            AdminModule adminModule = new AdminModule();
            adminModule.setId(moduleid);
            adminModule.setIsDelete(Constants.FLAG_IS_DELETE);
            Integer ret = this.adminModuleMapper.updateByPrimaryKeySelective(adminModule);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                result.setData(adminModule);
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    /**
     * 编辑模块信息：添加或修改
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void editModuleById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        AdminModule adminModule = JSON.parseObject(decodeJson, AdminModule.class);
        Integer ret;
        if (adminModule.getId() == null) {
            // new
            adminModule.setCreateTime(System.currentTimeMillis());
            adminModule.setUpdateTime(adminModule.getCreateTime());
            ret = this.adminModuleMapper.insertSelective(adminModule);
        } else {
            // update
            adminModule.setUpdateTime(System.currentTimeMillis());
            ret = this.adminModuleMapper.updateByPrimaryKeySelective(adminModule);
        }
        if (ret > 0) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }
}
