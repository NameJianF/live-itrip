package live.itrip.web.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.web.bean.BootStrapDataTableList;
import live.itrip.web.bean.PagerInfo;
import live.itrip.web.common.Constants;
import live.itrip.web.dao.WebCustomerAskMapper;
import live.itrip.web.model.WebCustomerAsk;
import live.itrip.web.service.BaseService;
import live.itrip.web.service.intefaces.IWebCustomerAskService;
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
 * Created by Feng on 2016/11/11.
 */
@Service
public class WebCustomerAskService extends BaseService implements IWebCustomerAskService {
    @Autowired
    private WebCustomerAskMapper webCustomerAskMapper;

    @Override
    public void insertCustomerAsk(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        try {
            WebCustomerAsk entiry = JSON.parseObject(decodeJson, WebCustomerAsk.class);
            entiry.setCreateTime(System.currentTimeMillis());
            entiry.setUpdateTime(entiry.getCreateTime());
            int ret = webCustomerAskMapper.insertSelective(entiry);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                this.writeResponse(response, result);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void selectCustomerAskList(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<WebCustomerAsk> result = new BootStrapDataTableList<>();
        try {
            PagerInfo pagerInfo = this.getPagerInfo(decodeJson);

            Subject subject = SecurityUtils.getSubject();
            subject.isPermitted();

            Integer count = webCustomerAskMapper.countAll();
            List<WebCustomerAsk> list = webCustomerAskMapper.selectCustomerAskList(pagerInfo.getStart(), pagerInfo.getLength());
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
    public void selectAskInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer orderId = jsonObject.getInteger("orderId");
        if (orderId != null) {
            WebCustomerAsk info = this.webCustomerAskMapper.selectByPrimaryKey(orderId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(info);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void editAskInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        WebCustomerAsk info = JSON.parseObject(decodeJson, WebCustomerAsk.class);
        Integer ret;

        // update
        info.setUpdateTime(System.currentTimeMillis());
        ret = this.webCustomerAskMapper.updateByPrimaryKeySelective(info);
        if (ret > 0) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteAskInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer orderId = (Integer) jsonObject.get("orderId");
        if (orderId != null) {
            WebCustomerAsk info = new WebCustomerAsk();
            info.setId(orderId);
            info.setIsDelete(Constants.FLAG_IS_DELETE);
            Integer ret = this.webCustomerAskMapper.updateByPrimaryKeySelective(info);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                result.setData(info);
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }


}
