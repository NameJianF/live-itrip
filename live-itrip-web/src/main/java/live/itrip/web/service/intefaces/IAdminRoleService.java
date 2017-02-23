package live.itrip.web.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/10/11.
 */
public interface IAdminRoleService {

    void selectRoles(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectRoleById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteRoleById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editRoleById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
