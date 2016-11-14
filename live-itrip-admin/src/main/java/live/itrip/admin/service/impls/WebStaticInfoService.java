package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.WebStaticInfoMapper;
import live.itrip.admin.model.WebStaticInfo;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IWebStaticInfoService;
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
import java.util.List;

/**
 * Created by Feng on 2016/11/13.
 */
@Service
public class WebStaticInfoService extends BaseService implements IWebStaticInfoService {
    @Autowired
    private WebStaticInfoMapper webStaticInfoMapper;


    @Override
    public List<WebStaticInfo> selectAllStaticInfos() {
        return webStaticInfoMapper.selectAllStaticInfos();
    }

    @Override
    public List<WebStaticInfo> selectAllIdAndTitle() {
        return webStaticInfoMapper.selectAllIdAndTitle();
    }

    @Override
    public void selectStaticInfoList(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<WebStaticInfo> result = new BootStrapDataTableList<>();
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

            Subject subject = SecurityUtils.getSubject();
            subject.isPermitted();

            Integer count = webStaticInfoMapper.countAll();
            List<WebStaticInfo> list = webStaticInfoMapper.selectStaticInfos(pagerInfo.getStart(), pagerInfo.getLength(), queryContent);
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
    public void selectStaticInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer infoId = jsonObject.getInteger("infoId");
        if (infoId != null) {
            WebStaticInfo info = this.webStaticInfoMapper.selectByPrimaryKey(infoId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(info);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void editStaticInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        WebStaticInfo info = JSON.parseObject(decodeJson, WebStaticInfo.class);
        Integer ret;
        if (info.getId() == null) {
            // new
            info.setCreateTime(System.currentTimeMillis());
            info.setUpdateTime(info.getCreateTime());
            ret = this.webStaticInfoMapper.insertSelective(info);
        } else {
            // update
            info.setUpdateTime(System.currentTimeMillis());
            ret = this.webStaticInfoMapper.updateByPrimaryKeySelective(info);
        }
        if (ret > 0) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteStaticInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer infoId = (Integer) jsonObject.get("infoId");
        if (infoId != null) {
            WebStaticInfo info = new WebStaticInfo();
            info.setId(infoId);
            info.setIsDelete(Constants.FLAG_IS_DELETE);
            Integer ret = this.webStaticInfoMapper.updateByPrimaryKeySelective(info);
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
