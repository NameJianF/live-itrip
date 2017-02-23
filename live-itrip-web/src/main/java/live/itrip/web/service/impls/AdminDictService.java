package live.itrip.web.service.impls;


import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.web.bean.BootStrapDataTableList;
import live.itrip.web.bean.PagerInfo;
import live.itrip.web.common.Constants;
import live.itrip.web.dao.AdminDictMapper;
import live.itrip.web.model.AdminDict;
import live.itrip.web.service.BaseService;
import live.itrip.web.service.intefaces.IAdminDictService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/7/21.
 * <p>
 * 字典 操作服务类
 */
@Service
public class AdminDictService extends BaseService implements IAdminDictService {
    @Autowired
    private AdminDictMapper adminDictMapper;

    /**
     * 查询全部未标记删除的数据
     *
     * @return
     */
    @Override
    public List<AdminDict> selectAllDicts() {
        return adminDictMapper.selectAllDicts("0");
    }

    /**
     * 查询全部的字典信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectDicts(String decodeJson, HttpServletResponse response, HttpServletRequest request) {

        BootStrapDataTableList<AdminDict> result = new BootStrapDataTableList<>();
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
            Integer count = adminDictMapper.countAll();
            List<AdminDict> dictList = adminDictMapper.selectDicts(queryContent, pagerInfo.getStart(), pagerInfo.getLength());
            if (dictList != null) {
                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(count);
                result.setiTotalDisplayRecords(count);
                result.setAaData(dictList);

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

    /**
     * 查询详细信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectDictById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer dictid = (Integer) jsonObject.get("dictid");
        if (dictid != null) {
            AdminDict adminDict = this.adminDictMapper.selectByPrimaryKey(dictid);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(adminDict);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    /**
     * 逻辑删除
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void deleteDictById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer dictid = (Integer) jsonObject.get("dictid");
        if (dictid != null) {
            AdminDict adminDict = new AdminDict();
            adminDict.setId(dictid);
            adminDict.setIsDelete(Constants.FLAG_IS_DELETE);
            Integer ret = this.adminDictMapper.updateByPrimaryKeySelective(adminDict);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                result.setData(adminDict);
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    /**
     * 编辑：添加或修改
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void editDictById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        AdminDict adminDict = JSON.parseObject(decodeJson, AdminDict.class);
        Integer ret;
        if (adminDict.getId() == null) {
            // new
            adminDict.setCreateTime(System.currentTimeMillis());
            adminDict.setUpdateTime(adminDict.getCreateTime());
            ret = this.adminDictMapper.insertSelective(adminDict);
        } else {
            // update
            adminDict.setUpdateTime(System.currentTimeMillis());
            ret = this.adminDictMapper.updateByPrimaryKeySelective(adminDict);
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
