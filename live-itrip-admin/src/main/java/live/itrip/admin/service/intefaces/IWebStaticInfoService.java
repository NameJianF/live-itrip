package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/11/13.
 */
public interface IWebStaticInfoService {
    void selectStaticInfoList(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectStaticInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editStaticInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteStaticInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
