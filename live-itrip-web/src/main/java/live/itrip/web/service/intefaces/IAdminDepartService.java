package live.itrip.web.service.intefaces;

import live.itrip.web.model.AdminDepart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
public interface IAdminDepartService {

    List<AdminDepart> selectAllDeparts();

    void selectDeparts(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectDepartById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteDepartById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editDepartById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
