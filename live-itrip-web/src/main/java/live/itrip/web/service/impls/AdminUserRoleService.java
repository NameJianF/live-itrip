package live.itrip.web.service.impls;

import live.itrip.web.dao.AdminRoleMapper;
import live.itrip.web.dao.AdminUserRoleMapper;
import live.itrip.web.model.AdminRole;
import live.itrip.web.model.AdminUserRole;
import live.itrip.web.service.BaseService;
import live.itrip.web.service.intefaces.IAdminUserRoleService;
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
