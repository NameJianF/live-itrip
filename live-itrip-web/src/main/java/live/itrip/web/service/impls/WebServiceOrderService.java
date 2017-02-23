package live.itrip.web.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.web.bean.BootStrapDataTableList;
import live.itrip.web.bean.PagerInfo;
import live.itrip.web.common.Constants;
import live.itrip.web.dao.WebServiceOrderMapper;
import live.itrip.web.model.WebServiceOrder;
import live.itrip.web.service.BaseService;
import live.itrip.web.service.intefaces.IWebServiceOrderService;
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
 * Created by Feng on 2016/11/21.
 */
@Service
public class WebServiceOrderService extends BaseService implements IWebServiceOrderService {

    @Autowired
    private WebServiceOrderMapper webServiceOrderMapper;

    /**
     * 记录客户订购服务的内容
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void insertServiceOrder(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        try {
            WebServiceOrder entiry = JSON.parseObject(decodeJson, WebServiceOrder.class);
            entiry.setCreateTime(System.currentTimeMillis());
            int ret = webServiceOrderMapper.insertSelective(entiry);
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
    public void selectOrderList(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<WebServiceOrder> result = new BootStrapDataTableList<>();
        try {
            PagerInfo pagerInfo = this.getPagerInfo(decodeJson);

            Subject subject = SecurityUtils.getSubject();
            subject.isPermitted();

            Integer count = webServiceOrderMapper.countAll();
            List<WebServiceOrder> list = webServiceOrderMapper.selectOrderList(pagerInfo.getStart(), pagerInfo.getLength());
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
    public void selectOrderInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer orderId = jsonObject.getInteger("orderId");
        if (orderId != null) {
            WebServiceOrder info = this.webServiceOrderMapper.selectByPrimaryKey(orderId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(info);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void editOrderInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        WebServiceOrder info = JSON.parseObject(decodeJson, WebServiceOrder.class);
        Integer ret;

        // update
        info.setUpdateTime(System.currentTimeMillis());
        ret = this.webServiceOrderMapper.updateByPrimaryKeySelective(info);
        if (ret > 0) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteOrderInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer orderId = (Integer) jsonObject.get("orderId");
        if (orderId != null) {
            WebServiceOrder info = new WebServiceOrder();
            info.setId(orderId);
            info.setIsDelete(Constants.FLAG_IS_DELETE);
            Integer ret = this.webServiceOrderMapper.updateByPrimaryKeySelective(info);
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
