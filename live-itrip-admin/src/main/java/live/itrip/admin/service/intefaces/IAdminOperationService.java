package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/10/11.
 */
public interface IAdminOperationService {
    void selectOperations(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectOperationById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteOperationById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editOperationById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
