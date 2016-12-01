package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.WebProductPlan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/11/17.
 */
public interface IWebProductPlanService {


    List<WebProductPlan> selectPlanList(Integer productId);

    void selectPlanList(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectPlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editPlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deletePlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectPlanDetailsByProductId(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
