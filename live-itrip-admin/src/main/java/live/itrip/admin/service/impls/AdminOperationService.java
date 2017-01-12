package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.dao.AdminOperationMapper;
import live.itrip.admin.model.AdminOperation;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminOperationService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
@Service
public class AdminOperationService extends BaseService implements IAdminOperationService {
    @Autowired
    private AdminOperationMapper adminOperationMapper;

    @Autowired
    public List<AdminOperation> selectAllOperations() {
        return adminOperationMapper.selectAllOperations();
    }


    @Override
    public void selectOperations(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<AdminOperation> result = new BootStrapDataTableList<>();
        try {
            PagerInfo pagerInfo = this.getPagerInfo(decodeJson);
            Integer count = adminOperationMapper.countAll();
            List<AdminOperation> list = adminOperationMapper.selectOperations(pagerInfo.getStart(), pagerInfo.getLength());
            if (list != null) {
                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(count);
                result.setiTotalDisplayRecords(count);
                result.setAaData(list);

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
    public void selectOperationById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer id = (Integer) jsonObject.get("operationId");
        if (id != null) {
            AdminOperation model = this.adminOperationMapper.selectByPrimaryKey(id);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(model);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteOperationById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer id = (Integer) jsonObject.get("operationId");
        if (id != null) {
            Integer ret = this.adminOperationMapper.deleteByPrimaryKey(id);
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
    public void editOperationById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        AdminOperation model = JSON.parseObject(decodeJson, AdminOperation.class);
        Integer ret;
        if (model.getId() == null) {
            // new
            model.setCreateTime(System.currentTimeMillis());
            model.setUpdateTime(model.getCreateTime());
            ret = this.adminOperationMapper.insertSelective(model);
        } else {
            // update
            model.setUpdateTime(System.currentTimeMillis());
            ret = this.adminOperationMapper.updateByPrimaryKeySelective(model);
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
