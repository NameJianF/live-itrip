package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.dao.WebCustomerAskMapper;
import live.itrip.admin.model.WebCustomerAsk;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IWebCustomerAskService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
