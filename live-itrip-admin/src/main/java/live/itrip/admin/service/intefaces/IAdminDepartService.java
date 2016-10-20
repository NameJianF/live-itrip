package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/10/11.
 */
public interface IAdminDepartService {
    void selectDeparts(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectDepartById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteDepartById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editDepartById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
