package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/11/11.
 */
public interface IWebCustomerAskService {
    void insertCustomerAsk(String decodeJson, HttpServletResponse response, HttpServletRequest request);

}
