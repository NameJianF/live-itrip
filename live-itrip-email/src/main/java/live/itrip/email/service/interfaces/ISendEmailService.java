package live.itrip.email.service.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2017/2/24.
 */
public interface ISendEmailService {

    void sendEmail(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
