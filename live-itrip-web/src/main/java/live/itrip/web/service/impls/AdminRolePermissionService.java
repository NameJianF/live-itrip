package live.itrip.web.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.web.dao.AdminRolePermissionMapper;
import live.itrip.web.model.AdminRolePermission;
import live.itrip.web.service.BaseService;
import live.itrip.web.service.intefaces.IAdminRolePermissionService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
@Service
public class AdminRolePermissionService extends BaseService implements IAdminRolePermissionService {
    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;

    /**
     * 查询角色权限信息
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectPermissionsByRoleId(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer roleId = (Integer) jsonObject.get("roleId");
        if (roleId != null) {
            List<HashMap<String, Object>> list = this.adminRolePermissionMapper.selectPermissionsByRoleId(roleId);
            JSONArray array = new JSONArray();
            for (HashMap<String, Object> per : list) {
                JSONObject object = new JSONObject();
                object.put("parentId", per.get("parent_id").toString());
                object.put("moduleId", per.get("module_id").toString());
                object.put("operation", per.get("operation").toString());
                array.add(object);
            }

            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(array);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    @Transactional
    public void modifyPermissionsByRoleId(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);

        Integer roleId = 0;
        if (!jsonObject.containsKey("roleId")) {
            this.paramInvalid(response, "roleId");
            return;
        }
        roleId = jsonObject.getInteger("roleId");

        if (jsonObject.containsKey("checkedElms")) {

            // delete all old
            adminRolePermissionMapper.deleteByRoleId(roleId);

            // add new
            List<AdminRolePermission> list = new ArrayList<>();
            JSONArray array = jsonObject.getJSONArray("checkedElms");
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                String id = object.getString("id");
                String[] strs = id.split("_");
                if (strs.length == 3) {
                    boolean contain = false;
                    for (AdminRolePermission per : list) {
                        if (per.getModuleId().equals(Integer.valueOf(strs[1]))) {
                            contain = true;
                            break;
                        }
                    }
                    if (contain) {
                        continue;
                    }
                    AdminRolePermission permission = new AdminRolePermission();
                    permission.setModuleId(Integer.valueOf(strs[1]));
                    permission.setOperation(getOperations(array, strs[1]));
                    permission.setRoleId(roleId);
                    permission.setCreateTime(System.currentTimeMillis());
                    permission.setUpdateTime(permission.getCreateTime());
                    list.add(permission);
                }
            }
            Integer rowcount = adminRolePermissionMapper.batchInsert(list);

            if (rowcount > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    private String getOperations(JSONArray array, String moduleid) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            String id = object.getString("id");
            String[] strs = id.split("_");
            if (strs.length == 3) {
                if (moduleid.equals(strs[1])) {
                    result.append(strs[2] + ";");
                }
            }

        }
        return result.toString().substring(0, result.toString().length() - 1);
    }
}
