package live.itrip.admin.service.impls;

import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.dao.WebProductMapper;
import live.itrip.admin.model.WebProduct;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IWebProductService;
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
 * Created by Feng on 2016/11/12.
 */
@Service
public class WebProductService extends BaseService implements IWebProductService {

    @Autowired
    private WebProductMapper webProductMapper;

    @Override
    public void selectProductList(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<WebProduct> result = new BootStrapDataTableList<WebProduct>();
        try {
            PagerInfo pagerInfo = this.getPagerInfo(decodeJson);

            Subject subject = SecurityUtils.getSubject();
            subject.isPermitted();

            Integer count = webProductMapper.countAll();
            List<WebProduct> moduleList = webProductMapper.selectProducts(pagerInfo.getStart(), pagerInfo.getLength());
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
}
