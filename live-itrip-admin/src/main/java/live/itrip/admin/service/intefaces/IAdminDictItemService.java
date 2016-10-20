package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/7/21.
 */
public interface IAdminDictItemService {
    void selectDictItems(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectDictItemById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteDictItemById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editDictItemById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
