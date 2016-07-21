package live.itrip.admin.service.impls;

import live.itrip.admin.dao.AdminModuleMapper;
import live.itrip.admin.model.AdminModule;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAdminModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Feng on 2016/7/21.
 * <p>
 * 模块操作 服务类
 */
@Service
public class AdminModuleService extends BaseService implements IAdminModuleService {
    @Autowired
    private AdminModuleMapper adminModuleMapper;

    /**
     * 查询模块信息
     *
     * @param flag 是否标记删除的， 删除：1，正常：0
     * @return
     */
    @Override
    public List<AdminModule> selectModules(String flag) {
        return adminModuleMapper.selectModules(flag);
    }
}
