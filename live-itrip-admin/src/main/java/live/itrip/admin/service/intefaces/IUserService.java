package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2016/7/7.
 */
public interface IUserService {
    void login(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectModules(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
