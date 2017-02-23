package live.itrip.web.service.intefaces;

import live.itrip.web.model.WebStaticInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Feng on 2016/11/13.
 */
public interface IWebStaticInfoService {
    void selectStaticInfoList(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectStaticInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editStaticInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteStaticInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    List<WebStaticInfo> selectAllStaticInfos();

    List<WebStaticInfo> selectAllIdAndTitle();
}
