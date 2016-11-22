package live.itrip.admin.controller;

import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.service.intefaces.IWebFileInfoService;
import live.itrip.common.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/10/12.
 * <p>
 * 文件操作
 */
@Controller
public class FileController extends AbstractController {
    @Autowired
    private IWebFileInfoService iWebFileInfoService;


    /**
     * @param response
     * @param request
     */
    @RequestMapping("/file/upload")
    public
    @ResponseBody
    void fileUpload(HttpServletResponse response, HttpServletRequest request) {
        String flag = request.getParameter("flag");

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            if (StringUtils.isNotEmpty(flag)) {
                iWebFileInfoService.uploadFile(flag, multipartRequest, response);
            }

        } catch (Exception ex) {
            Logger.error("file upload error:", ex);
        }
    }
}
