package live.itrip.admin.service.intefaces;

import live.itrip.admin.model.WebCityInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/11/23.
 */
public interface IWebCityInfoService {
    WebCityInfo selectCityInfoById(Integer id);

    void selectCityList(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectCityInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editCityInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteCityInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
