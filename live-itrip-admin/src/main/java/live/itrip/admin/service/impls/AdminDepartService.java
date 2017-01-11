package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.AdminDepartMapper;
import live.itrip.admin.model.AdminDepart;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminDepartService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
@Service
public class AdminDepartService extends BaseService implements IAdminDepartService {
    @Autowired
    private AdminDepartMapper adminDepartMapper;


    @Override
    public List<AdminDepart> selectAllDeparts() {
        return adminDepartMapper.selectDeparts("", 0, 1000);
    }

    @Override
    public void selectDeparts(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<AdminDepart> result = new BootStrapDataTableList<>();
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
            Integer count = adminDepartMapper.countAll();
            List<AdminDepart> departList = adminDepartMapper.selectDeparts(queryContent, pagerInfo.getStart(), pagerInfo.getLength());
            if (departList != null) {
                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(count);
                result.setiTotalDisplayRecords(count);
                result.setAaData(departList);

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

    @Override
    public void selectDepartById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer departId = (Integer) jsonObject.get("departId");
        if (departId != null) {
            AdminDepart adminDepart = this.adminDepartMapper.selectByPrimaryKey(departId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(adminDepart);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteDepartById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer departId = (Integer) jsonObject.get("departId");
        if (departId != null) {
            Integer ret = this.adminDepartMapper.deleteByPrimaryKey(departId);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void editDepartById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        AdminDepart adminDepart = JSON.parseObject(decodeJson, AdminDepart.class);
        Integer ret;
        if (adminDepart.getId() == null) {
            // new
            adminDepart.setCreateTime(System.currentTimeMillis());
            adminDepart.setUpdateTime(adminDepart.getCreateTime());
            ret = this.adminDepartMapper.insertSelective(adminDepart);
        } else {
            // update
            adminDepart.setUpdateTime(System.currentTimeMillis());
            ret = this.adminDepartMapper.updateByPrimaryKeySelective(adminDepart);
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
