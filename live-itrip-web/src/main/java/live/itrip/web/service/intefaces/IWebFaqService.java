package live.itrip.web.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/11/23.
 */
public interface IWebFaqService {
    void selectFaqList(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectFaqInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editFaqInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteFaqInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
