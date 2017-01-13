package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/10/11.
 */
public interface IAdminRolePermissionService {
    void selectPermissionsByRoleId(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void modifyPermissionsByRoleId(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
