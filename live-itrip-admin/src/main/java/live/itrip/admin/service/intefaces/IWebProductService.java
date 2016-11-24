package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/11/12.
 */
public interface IWebProductService {
    void selectProductList(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void editProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectHotProductsByCityId(String decodeJson, HttpServletResponse response, HttpServletRequest request);
}
