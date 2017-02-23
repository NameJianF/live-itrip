package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Feng on 2016/10/12.
 */
public interface IUserService {

    void userLogin(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void userLogout(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws IOException;

}
