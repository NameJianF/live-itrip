package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.common.Config;
import live.itrip.admin.common.ViewConstants;
import live.itrip.admin.dao.WebFileInfoMapper;
import live.itrip.admin.model.WebFileInfo;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IWebFileInfoService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.file.FileUtils;
import live.itrip.common.response.BaseResult;
import live.itrip.common.security.Md5Utils;
import live.itrip.common.util.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.util.Map;

/**
 * Created by Feng on 2016/11/17.
 */
@Service
public class WebFileInfoService extends BaseService implements IWebFileInfoService {
    @Autowired
    private WebFileInfoMapper webFileInfoMapper;


    @Override
    public void uploadFile(String flag, MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws IOException {
        BaseResult result = new BaseResult();
        // 参数
        String ownId = null;
        String own = null;
        String md5 = null;

        if (ViewConstants.FileParams.PRODUCT_0.equals(flag)) {
            // 产品上传
            ownId = multipartRequest.getParameter("productId");
            own = ViewConstants.FileParams.PRODUCT_0;
        } else if (ViewConstants.FileParams.PRODUCT_1.equals(flag)) {
            ownId = multipartRequest.getParameter("cityId");
            own = ViewConstants.FileParams.PRODUCT_1;
        }


        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (MultipartFile multipartFile : fileMap.values()) {
            try {
                // 原始文件名
                String originalFilename = multipartFile.getOriginalFilename();
                // 文件后缀
                String fileExt = "jpg";

                if (originalFilename.equalsIgnoreCase("blob")) {
                    String contentType = multipartFile.getContentType();
                    // 文件后缀
                    fileExt = contentType.substring(contentType.lastIndexOf("/") + 1).toLowerCase();
                } else {
                    // 文件后缀
                    fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
                }

                // 本地相对路径：新文件名
                String fileUrl = getLocalFilePath(fileExt);
                Logger.debug("File url:" + fileUrl);

                // 文件本地文件绝对路径：新文件名
                String filepath = multipartRequest.getSession().getServletContext().getRealPath(fileUrl);
                Logger.debug("File Local Path:" + filepath);

                // 保存文件到本地
                File file = FileUtils.saveFile(filepath, multipartFile.getBytes());

                md5 = Md5Utils.getFileMD5(file);

                WebFileInfo fileInfo = new WebFileInfo();
                fileInfo.setMd5(md5);
                fileInfo.setOwn(own);
                if (StringUtils.isNotEmpty(ownId)) {
                    fileInfo.setOwnId(Integer.valueOf(ownId));
                }
                fileInfo.setFileName(originalFilename);
                fileInfo.setFileLocation(filepath);
                fileInfo.setFileSize(multipartFile.getSize());
                fileInfo.setFileType(fileExt);
                fileInfo.setFileUrl(fileUrl);
                fileInfo.setCreateTime(System.currentTimeMillis());
                fileInfo.setUpdateTime(fileInfo.getCreateTime());

                // 保存文件到数据库
                int ret = webFileInfoMapper.insertSelective(fileInfo);
                if (ret > 0) {
                    // 文件保存成功
                    result.setCode(ErrorCode.SUCCESS.getCode());
                    JSONObject data = new JSONObject();
                    data.put("fileUrl", fileInfo.getFileUrl());
                    data.put("fileId", fileInfo.getId());
                    result.setData(data);
                    this.writeResponse(response, result);
                    return;
                }

            } catch (Exception ex) {
                Logger.error(ex.getMessage(), ex);
            }
        }


        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    /**
     * 添加文件信息中的 productid
     *
     * @param decodeJson
     * @param productId
     */
    @Override
    public void modifyFileInfoById(String decodeJson, Integer productId) {
        try {
            JSONObject object = JSON.parseObject(decodeJson);
            String productImgSamllId = object.getString("productImgSamllId");
            String productImgBigId = object.getString("productImgBigId");

            if (StringUtils.isNotEmpty(productImgSamllId)) {
                WebFileInfo fileInfo = new WebFileInfo();
                fileInfo.setId(Long.valueOf(productImgSamllId));
                fileInfo.setOwnId(productId);
                this.webFileInfoMapper.updateByPrimaryKeySelective(fileInfo);
            }
            if (StringUtils.isNotEmpty(productImgBigId)) {
                WebFileInfo fileInfo = new WebFileInfo();
                fileInfo.setId(Long.valueOf(productImgBigId));
                fileInfo.setOwnId(productId);
                this.webFileInfoMapper.updateByPrimaryKeySelective(fileInfo);
            }
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }
    }


    /**
     * 设置文件本地路径
     *
     * @param ext 文件后缀
     * @return
     * @throws IOException
     */
    private String getLocalFilePath(String ext) {
        // local + 201610 + filename
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        return Config.FILE_SAVE_PATH + df.format(new Date()) + File.separator + UuidUtils.getUuidLowerCase(false) + "." + ext;
    }
}
