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
    public static String decoderForJsonString(String json) {
        // url 解码
        String decodeJson = "";
        try {
            json = json.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            json = json.replaceAll("\\+", "%2B");
            decodeJson = URLDecoder.decode(json, "UTF-8");

//            String tmp = URLDecoder.decode(json.replace("=", ""),Encoding.UTF8);
//            decodeJson = tmp.substring(0, tmp.length());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodeJson;
    }

    /**
     * json url 编码
     *
     * @param decodeJson
     * @return
     */
    public static String encoderString(String decodeJson) {
        // url 编码
        try {
            return URLEncoder.encode(decodeJson, Encoding.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
