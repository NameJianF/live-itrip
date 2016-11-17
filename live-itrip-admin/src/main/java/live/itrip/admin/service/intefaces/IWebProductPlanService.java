package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/11/17.
 */
public interface IWebProductPlanService {
    void selectPlanList(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectPlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editPlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deletePlanById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
