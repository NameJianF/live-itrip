package live.itrip.admin.service.impls;

import live.itrip.admin.dao.ClientApiKeyMapper;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IClientApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/7/14.
 * <p>
 * apikey 服务类
 */
@Service
public class ClientApiKeyServiceImpl extends BaseService implements IClientApiKeyService {
    @Autowired
    private ClientApiKeyMapper clientApiKeyMapper;

    /**
     * 查询apikey 列表
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectKeyList(String decodeJson, HttpServletResponse response, HttpServletRequest request) {

    }
}
