package live.itrip.web.service.intefaces;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Feng on 2016/11/17.
 */
public interface IWebFileInfoService {
    void uploadFile(String flag, MultipartHttpServletRequest fileMap, HttpServletResponse response) throws IOException;

    void modifyFileInfoById(String decodeJson, Integer productId);
}
