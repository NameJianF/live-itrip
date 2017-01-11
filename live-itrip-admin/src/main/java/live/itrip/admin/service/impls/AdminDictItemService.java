package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.AdminDictItemMapper;
import live.itrip.admin.dao.AdminDictMapper;
import live.itrip.admin.model.AdminDict;
import live.itrip.admin.model.AdminDictItem;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminDictItemService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/7/21.
 * <p>
 * 字典项 操作服务类
 */
@Service
public class AdminDictItemService extends BaseService implements IAdminDictItemService {
    @Autowired
    private AdminDictItemMapper adminDictItemMapper;
    @Autowired
    private AdminDictMapper adminDictMapper;


    @Override
    public List<AdminDictItem> selectAllDictItems() {
        return adminDictItemMapper.selectAllDictItems();
    }

    @Override
    public void selectDictItems(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<AdminDictItem> result = new BootStrapDataTableList<>();
        try {
            // 解析查询条件
            JSONArray jsonarray = JSONArray.parseArray(decodeJson);
            String queryContent = null;
            Integer queryDictId = null;
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
                if (obj.get("name").equals("queryContent")) {
                    queryContent = obj.get("value").toString();
                } else if (obj.get("name").equals("queryDictId")) {
                    queryDictId = obj.getInteger("value");
                }
            }
            if (StringUtils.isNotEmpty(queryContent)) {
                queryContent = "'%" + queryContent.trim() + "%'";
            }

            PagerInfo pagerInfo = this.getPagerInfo(jsonarray);
            Integer count = adminDictItemMapper.countAll();
            List<AdminDictItem> dictItemList = adminDictItemMapper.selectDictItems(queryDictId, queryContent, pagerInfo.getStart(), pagerInfo.getLength());
            if (dictItemList != null) {

                List<AdminDict> dicts = adminDictMapper.selectAllDicts("0");
                for (AdminDictItem item : dictItemList) {
                    for (AdminDict dict : dicts) {
                        if (item.getDictId().equals(dict.getId())) {
                            item.setDictName(dict.getDictText());
                            break;
                        }
                    }
                }

                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(count);
                result.setiTotalDisplayRecords(count);
                result.setAaData(dictItemList);

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
    public void selectDictItemById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer dictItemId = (Integer) jsonObject.get("dictItemId");
        if (dictItemId != null) {
            AdminDictItem adminDictItem = this.adminDictItemMapper.selectByPrimaryKey(dictItemId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(adminDictItem);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteDictItemById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer dictItemId = (Integer) jsonObject.get("dictItemId");
        if (dictItemId != null) {
            AdminDictItem adminDictItem = new AdminDictItem();
            adminDictItem.setId(dictItemId);
            adminDictItem.setIsDelete(Constants.FLAG_IS_DELETE);
            Integer ret = this.adminDictItemMapper.updateByPrimaryKeySelective(adminDictItem);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                result.setData(adminDictItem);
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void editDictItemById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        AdminDictItem adminDictItem = JSON.parseObject(decodeJson, AdminDictItem.class);
        Integer ret;
        if (adminDictItem.getId() == null) {
            // new
            adminDictItem.setCreateTime(System.currentTimeMillis());
            adminDictItem.setUpdateTime(adminDictItem.getCreateTime());
            ret = this.adminDictItemMapper.insertSelective(adminDictItem);
        } else {
            // update
            adminDictItem.setUpdateTime(System.currentTimeMillis());
            ret = this.adminDictItemMapper.updateByPrimaryKeySelective(adminDictItem);
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
