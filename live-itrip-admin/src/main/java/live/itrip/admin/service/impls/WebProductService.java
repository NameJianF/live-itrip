package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.WebProductMapper;
import live.itrip.admin.model.WebProduct;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IWebFileInfoService;
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
    @Autowired
    private IWebFileInfoService iWebFileInfoService;

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

    @Override
    public void selectProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer productId = jsonObject.getInteger("productId");
        if (productId != null) {
            WebProduct info = this.webProductMapper.selectByPrimaryKey(productId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(info);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void editProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        WebProduct info = JSON.parseObject(decodeJson, WebProduct.class);
        Integer ret;
        if (info.getId() == null) {
            // new
            info.setCreateTime(System.currentTimeMillis());
            info.setUpdateTime(info.getCreateTime());
            ret = this.webProductMapper.insertSelective(info);
            if (ret > 0) {
                // 更新文件表数据
                iWebFileInfoService.modifyFileInfoById(decodeJson, info.getId());

                result.setCode(ErrorCode.SUCCESS.getCode());
                JSONObject object = new JSONObject();
                object.put("id", info.getId());
                result.setData(object);

                this.writeResponse(response, result);
                return;
            }
        } else {
            // update
            info.setUpdateTime(System.currentTimeMillis());
            ret = this.webProductMapper.updateByPrimaryKeySelective(info);
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
    public void deleteProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer productId = (Integer) jsonObject.get("productId");
        if (productId != null) {
            WebProduct info = new WebProduct();
            info.setId(productId);
            info.setIsDelete(Constants.FLAG_IS_DELETE);
            Integer ret = this.webProductMapper.updateByPrimaryKeySelective(info);
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

    /**
     * 根据 城市 查询相关推荐产品
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectHotProductsByCityId(String decodeJson, HttpServletResponse response, HttpServletRequest request) {

    }
}
