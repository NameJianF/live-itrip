package live.itrip.admin.service.impls;

import live.itrip.admin.dao.AdminRoleMapper;
import live.itrip.admin.dao.AdminUserRoleMapper;
import live.itrip.admin.model.AdminRole;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Feng on 2016/7/29.
 */
@Service
public class AdminUserRoleService extends BaseService implements IAdminUserRole {

    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * @param userId
     * @return
     */
    @Override
    public List<AdminRole> selectRolesByUserId(Long userId) {
        return adminRoleMapper.selectRolesByUserId(userId);
    }
}
