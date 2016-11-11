package live.itrip.common.util;

import live.itrip.common.Encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by 建锋 on 2016/7/7.
 */
public class JsonStringUtils {
    /**
     * json url解码
     *
     * @param json
     * @return
     */
    public static String URLDecoderForJsonString(String json) {
        return json;
        // url 解码
//        String decodeJson = "";
//        try {
//            String tmp = URLDecoder.decode(json.replace("=", ""),
//                    Encoding.UTF8);
//            decodeJson = tmp.substring(0, tmp.length());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return decodeJson;
    }

    /**
     * json url 编码
     *
     * @param decodeJson
     * @return
     */
    public static String URLEncoderString(String decodeJson) {
        // url 编码
        try {
            return URLEncoder.encode(decodeJson, Encoding.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
