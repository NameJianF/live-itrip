package live.itrip.admin.service.impls;

import live.itrip.admin.dao.AdminPermissionMapper;
import live.itrip.admin.dao.AdminRolePermissionMapper;
import live.itrip.admin.model.AdminPermission;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Feng on 2016/7/29.
 */
@Service
public class AdminRolePermissionService extends BaseService implements IAdminRolePermission {

    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    @Override
    public List<AdminPermission> selectPermissionsByRoleId(Long roleId) {
        return adminPermissionMapper.selectPermissionsByRoleId(roleId);
    }
}
