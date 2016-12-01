package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.WebProductPlanMapper;
import live.itrip.admin.model.WebProductPlan;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IWebProductPlanService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/11/17.
 */
@Service
public class WebProductPlanService extends BaseService implements IWebProductPlanService {
    @Autowired
    private WebProductPlanMapper webProductPlanMapper;

    /**
     * for preview
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectPlanList(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer productId = jsonObject.getInteger("productId");
        if (productId != null) {
            List<WebProductPlan> list = selectPlanList(productId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(list);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    /**
     * @param productId
     */
    @Override
    public List<WebProductPlan> selectPlanList(Integer productId) {
        if (productId != null) {
            return webProductPlanMapper.selectPlanDetailsByProductId(productId);
        }
        return null;
    }

    /**
     * for table
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectPlanDetailsByProductId(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<WebProductPlan> result = new BootStrapDataTableList<>();
        try {

            // 解析查询条件
            JSONArray jsonarray = JSONArray.parseArray(decodeJson);
            Integer productId = 0;
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
                if (obj.get("name").equals("productId")) {
                    productId = obj.getInteger("value");
                }
            }

            PagerInfo pagerInfo = this.getPagerInfo(jsonarray);

            Subject subject = SecurityUtils.getSubject();
            subject.isPermitted();

            if (productId == null) {
                productId = 0;
            }

            Integer count = webProductPlanMapper.countByProductId(productId);
            List<WebProductPlan> list = webProductPlanMapper.selectPlanDetailsByProductId(productId);
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
    public void selectPlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer planId = (Integer) jsonObject.get("planId");
        if (planId != null) {
            WebProductPlan info = this.webProductPlanMapper.selectByPrimaryKey(planId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(info);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void editPlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        WebProductPlan info = JSON.parseObject(decodeJson, WebProductPlan.class);
        Integer ret;
        if (info.getId() == null) {
            // new
            info.setCreateTime(System.currentTimeMillis());
            info.setUpdateTime(info.getCreateTime());
            ret = this.webProductPlanMapper.insertSelective(info);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                this.writeResponse(response, result);
                return;
            }
        } else {
            // update
            info.setUpdateTime(System.currentTimeMillis());
            ret = this.webProductPlanMapper.updateByPrimaryKeySelective(info);
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
    public void deletePlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer planId = (Integer) jsonObject.get("planId");
        if (planId != null) {
            Integer ret = this.webProductPlanMapper.deleteByPrimaryKey(planId);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

}
