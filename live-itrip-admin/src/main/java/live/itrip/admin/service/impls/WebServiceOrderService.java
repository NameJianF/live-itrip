package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.dao.WebServiceOrderMapper;
import live.itrip.admin.model.WebServiceOrder;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IWebServiceOrderService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
