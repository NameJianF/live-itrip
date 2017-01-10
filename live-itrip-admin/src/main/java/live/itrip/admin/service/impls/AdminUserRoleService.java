package live.itrip.admin.service.impls;

import live.itrip.admin.dao.AdminRoleMapper;
import live.itrip.admin.dao.AdminUserRoleMapper;
import live.itrip.admin.model.AdminRole;
import live.itrip.admin.model.AdminUserRole;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
@Service
public class AdminUserRoleService extends BaseService implements IAdminUserRoleService {
    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public List<AdminRole> selectRolesByUserId(Long userId) {

        return adminUserRoleMapper.selectRoleNameByUserId(userId);
    }
}
