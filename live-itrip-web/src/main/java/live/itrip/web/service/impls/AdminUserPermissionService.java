package live.itrip.web.service.impls;


import live.itrip.web.dao.AdminUserPermissionMapper;
import live.itrip.web.model.AdminUserPermission;
import live.itrip.web.service.BaseService;
import live.itrip.web.service.intefaces.IAdminUserPermissionService;
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
