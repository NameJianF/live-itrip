package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.common.BootStrapDataTableList;
import live.itrip.admin.common.PagerInfo;
import live.itrip.admin.dao.AdminModuleMapper;
import live.itrip.admin.model.AdminModule;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminModuleService;
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
import java.util.List;

/**
 * Created by Feng on 2016/7/21.
 * <p>
 * 模块操作 服务类
 */
@Service
public class AdminModuleService extends BaseService implements IAdminModuleService {
    @Autowired
    private AdminModuleMapper adminModuleMapper;

    /**
     * 查询模块信息
     *
     * @param flag 是否标记删除的， 删除：1，正常：0
     * @return
     */
    @Override
    public List<AdminModule> selectModules(String flag) {
        if (StringUtils.isEmpty(flag)) {
            return adminModuleMapper.selectAllModules();
        }
        return adminModuleMapper.selectModules(flag);
    }

    /**
     * 查询模块信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectModules(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<AdminModule> result = new BootStrapDataTableList<AdminModule>();
        try {
            PagerInfo pagerInfo = this.getPagerInfo(decodeJson);
            int totalecords = 10;

            Subject subject = SecurityUtils.getSubject();
            subject.isPermitted();

            List<AdminModule> moduleList = adminModuleMapper.selectAllModules();
            if (moduleList != null) {
                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(totalecords);
                result.setiTotalDisplayRecords(totalecords);
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
}
