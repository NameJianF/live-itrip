package live.itrip.web.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.web.bean.BootStrapDataTableList;
import live.itrip.web.bean.PagerInfo;
import live.itrip.web.dao.AdminGroupMapper;
import live.itrip.web.model.AdminGroup;
import live.itrip.web.model.extend.ExtendAdminGroup;
import live.itrip.web.service.BaseService;
import live.itrip.web.service.intefaces.IAdminGroupService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
@Service
public class AdminGroupService extends BaseService implements IAdminGroupService {
    @Autowired
    private AdminGroupMapper adminGroupMapper;


    @Override
    public List<ExtendAdminGroup> selectAllGroups() {
        return adminGroupMapper.selectAllGroups();
    }

    @Override
    public void selectGroups(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<ExtendAdminGroup> result = new BootStrapDataTableList<>();
        try {
            PagerInfo pagerInfo = this.getPagerInfo(decodeJson);
            Integer count = adminGroupMapper.countAll();
            List<ExtendAdminGroup> list = adminGroupMapper.selectGroups(pagerInfo.getStart(), pagerInfo.getLength());
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
    public void selectGroupById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer id = (Integer) jsonObject.get("groupId");
        if (id != null) {
            AdminGroup model = this.adminGroupMapper.selectByPrimaryKey(id);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(model);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteGroupById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer id = (Integer) jsonObject.get("groupId");
        if (id != null) {
            Integer ret = this.adminGroupMapper.deleteByPrimaryKey(id);
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
    public void editGroupById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        AdminGroup model = JSON.parseObject(decodeJson, AdminGroup.class);
        Integer ret;
        if (model.getId() == null) {
            // new
            model.setCreateTime(System.currentTimeMillis());
            model.setUpdateTime(model.getCreateTime());
            ret = this.adminGroupMapper.insertSelective(model);
        } else {
            // update
            model.setUpdateTime(System.currentTimeMillis());
            ret = this.adminGroupMapper.updateByPrimaryKeySelective(model);
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
    public void selectGroupsByDepartId(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        List<ExtendAdminGroup> list = this.selectAllGroups();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer departId = jsonObject.getInteger("departId");
        if (departId != null) {
            if (list != null) {
                JSONArray array = new JSONArray();
                for (ExtendAdminGroup extendAdminGroup : list) {
                    if (extendAdminGroup.getDepartId().equals(departId)) {
                        JSONObject object = new JSONObject();
                        object.put("groupId", extendAdminGroup.getId());
                        object.put("groupName", extendAdminGroup.getGroupName());
                        array.add(object);
                    }
                }
                result.setCode(ErrorCode.SUCCESS.getCode());
                result.setData(array);
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }
}
