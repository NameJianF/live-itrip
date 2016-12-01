package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.common.Constants;
import live.itrip.admin.common.ViewConstants;
import live.itrip.admin.dao.WebProductMapper;
import live.itrip.admin.model.WebProduct;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IWebFileInfoService;
import live.itrip.admin.service.intefaces.IWebProductService;
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
import java.util.ArrayList;
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
    public WebProduct selectProductById(Integer productId) {
        return this.webProductMapper.selectByPrimaryKey(productId);
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
     * <p>
     * 1. 查询城市相关的产品
     * 2. 按照点击 参与人数倒序排列
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectHotProductsByCityId(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
//        Integer cityId = jsonObject.getInteger("cityId");
        String cityName = jsonObject.getString("cityName");

        if (StringUtils.isEmpty(cityName)) {
            cityName = "东京";
        }
        int topCount = 5;
        List<WebProduct> list = this.webProductMapper.selectHotProductsByCity(topCount, cityName);

        if (list == null || list.size() == 0) {
            list = this.webProductMapper.selectHotProductsByCity(topCount, "");
        }

        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setData(list);
        this.writeResponse(response, result);
    }

    /**
     * 查询产品相关产品列表
     * <p>
     * 1. 查询同产品类型下的其他产品
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectProductListAbouts(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer productId = jsonObject.getInteger("productId");
        String productType = jsonObject.getString("productType");

        if (productId == null) {
            productId = 0;
        }
        if (StringUtils.isEmpty(productType)) {
            productType = ViewConstants.ProductType.Free;
        }
        int topCount = 5;
        List<WebProduct> list = this.webProductMapper.selectListAbouts(topCount, productId, productType);

        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setData(list);
        this.writeResponse(response, result);
    }


    @Override
    public void selectProductListByType(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        String flag = jsonObject.getString("flag");
        if (StringUtils.isNotEmpty(flag)) {
            int topCount = 4;
            List<WebProduct> list = null;
            if ("0".equals(flag)) {
                // 乡村民宿
                list = this.webProductMapper.selectListByType(topCount, ViewConstants.ProductType.Town);
            } else if ("1".equals(flag)) {
                // 温泉旅游
                list = this.webProductMapper.selectListByType(topCount, ViewConstants.ProductType.HotSpring);
            } else if ("2".equals(flag)) {
                // 滑雪之行
                list = this.webProductMapper.selectListByType(topCount, ViewConstants.ProductType.Skiing);
            } else if ("3".equals(flag)) {
                // 海岛旅游
                list = this.webProductMapper.selectListByType(topCount, ViewConstants.ProductType.Island);
            } else if ("4".equals(flag)) {
                // 快乐家族
                list = this.webProductMapper.selectListByType(topCount, ViewConstants.ProductType.Family);
            } else if ("5".equals(flag)) {
                // 见学体验
                list = this.webProductMapper.selectListByType(topCount, ViewConstants.ProductType.Learning);
            } else if ("6".equals(flag)) {
                // 健康检查
                list = this.webProductMapper.selectListByType(topCount, ViewConstants.ProductType.Health);
            }


            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(list);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }


}
