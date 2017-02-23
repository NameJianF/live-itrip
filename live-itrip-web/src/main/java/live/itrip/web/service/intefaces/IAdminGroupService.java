package live.itrip.web.service.intefaces;

import live.itrip.web.model.extend.ExtendAdminGroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/10/11.
 */
public interface IAdminGroupService {

    List<ExtendAdminGroup> selectAllGroups();

    void selectGroups(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectGroupById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteGroupById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editGroupById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectGroupsByDepartId(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
