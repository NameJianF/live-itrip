package live.itrip.admin.service.impls;

import live.itrip.admin.api.sso.bean.User;
import live.itrip.admin.dao.AdminUserMapper;
import live.itrip.admin.model.AdminUser;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
