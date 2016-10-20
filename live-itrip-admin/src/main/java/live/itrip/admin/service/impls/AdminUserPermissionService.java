package live.itrip.admin.service.impls;

import live.itrip.admin.api.sso.bean.User;
import live.itrip.admin.dao.AdminUserPermissionMapper;
import live.itrip.admin.model.AdminUserPermission;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Feng on 2016/10/12.
 */
@Service
public class AdminUserPermissionService extends BaseService implements IAdminUserPermissionService {

    @Autowired
    private AdminUserPermissionMapper adminUserPermissionMapper;

    @Override
    public List<AdminUserPermission> selectUserPermissionsByUserId(Long userId) {
        return adminUserPermissionMapper.selectUserPermissionsByUserId(userId);
    }

}
