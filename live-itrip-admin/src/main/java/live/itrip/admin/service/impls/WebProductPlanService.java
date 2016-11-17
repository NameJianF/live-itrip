package live.itrip.admin.service.impls;

import live.itrip.admin.dao.WebProductPlanMapper;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IWebProductPlanService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/11/17.
 */
public class WebProductPlanService extends BaseService implements IWebProductPlanService {
    @Autowired
    private WebProductPlanMapper webProductPlanMapper;

    @Override
    public void selectPlanList(String decodeJson, HttpServletResponse response, HttpServletRequest request) {

    }

    @Override
    public void selectPlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {

    }

    @Override
    public void editPlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {

    }

    @Override
    public void deletePlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {

    }
}
