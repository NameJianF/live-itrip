package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.api.sso.bean.User;
import live.itrip.admin.bean.BootStrapDataTableList;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.AdminUserMapper;
import live.itrip.admin.model.AdminGroup;
import live.itrip.admin.model.AdminUser;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminUserService;
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
 * Created by Feng on 2016/10/24.
 */
@Service
public class AdminUserService extends BaseService implements IAdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser selectAdminUserById(Long userId) {
        return adminUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public AdminUser saveUserInfo(User user) {
        AdminUser record = new AdminUser();
        record.setId(user.getId());
        record.setUserName(user.getUserName());
        record.setEmail(user.getEmail());
        record.setMobile(user.getMobile());
//        record.setDepartId();
//        record.setDepartName();
//        record.setGroupId();
//        record.setGroupName();
        record.setLevel(user.getLevel());
        record.setStatus(user.getStatus());
        record.setIdentity(user.getIdentity());
        record.setCreateTime(System.currentTimeMillis());
//        record.setUpdateTime(record.getCreateTime());
        Integer ret = adminUserMapper.insert(record);
        if (ret > 0) {
            return record;
        }
        return null;
    }

    @Override
    public void selectAdminUsers(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<AdminUser> result = new BootStrapDataTableList<>();
        try {
            // 解析查询条件
            JSONArray jsonarray = JSONArray.parseArray(decodeJson);
            String queryUserName = null;
            Integer queryDepart = null;
            Integer queryGroup = null;
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
                if (obj.get("name").equals("queryUserName")) {
                    queryUserName = obj.get("value").toString();
                } else if (obj.get("name").equals("queryDepart")) {
                    queryDepart = obj.getInteger("value");
                } else if (obj.get("name").equals("queryGroup")) {
                    queryGroup = obj.getInteger("value");
                }
            }


            PagerInfo pagerInfo = this.getPagerInfo(jsonarray);
            Integer count = adminUserMapper.countAll();
            if (StringUtils.isNotEmpty(queryUserName)) {
                queryUserName = "'%" + queryUserName.trim() + "%'";
            }
            List<AdminUser> userList = adminUserMapper.selectAdminUsers(queryDepart, queryGroup, queryUserName, pagerInfo.getStart(), pagerInfo.getLength());

            if (userList != null) {
                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(count);
                result.setiTotalDisplayRecords(count);
                result.setAaData(userList);

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
    public void selectAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Long memberId = Long.valueOf(jsonObject.get("memberId").toString());
        if (memberId != null) {
            AdminUser AdminUser = this.adminUserMapper.selectByPrimaryKey(memberId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(AdminUser);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Long memberId = Long.valueOf(jsonObject.get("memberId").toString());
        if (memberId != null) {
            Integer ret = this.adminUserMapper.deleteByPrimaryKey(memberId);
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
    public void editAdminUserById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        AdminUser AdminUser = JSON.parseObject(decodeJson, AdminUser.class);
        Integer ret;
        if (AdminUser.getId() == null) {
            // new
            AdminUser.setCreateTime(System.currentTimeMillis());
            ret = this.adminUserMapper.insertSelective(AdminUser);
        } else {
            // update
            ret = this.adminUserMapper.updateByPrimaryKeySelective(AdminUser);
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
