package live.itrip.web.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/11/21.
 */
public interface IWebServiceOrderService {
    void insertServiceOrder(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectOrderList(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectOrderInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editOrderInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteOrderInfoById(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
