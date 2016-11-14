package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.AdminDictItem;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/7/21.
 */
public interface IAdminDictItemService {
    List<AdminDictItem> selectAllDictItems();

    void selectDictItems(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectDictItemById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteDictItemById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editDictItemById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

}
