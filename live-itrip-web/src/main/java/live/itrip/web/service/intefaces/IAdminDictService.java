package live.itrip.web.service.intefaces;

import live.itrip.web.model.AdminDict;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Created by Feng on 2016/7/21.
 */
public interface IAdminDictService {
    /**
     * 查询全部未标记删除的数据
     *
     * @return
     */
    List<AdminDict> selectAllDicts();

    void selectDicts(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectDictById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteDictById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editDictById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
