package live.itrip.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class SigUtils {

    /**
     * 获取数字签名信息
     *
     * @param json
     * @param secretkey
     * @return
     */
    public static String getSig(String json, String secretkey) {
        StringBuffer buffer = new StringBuffer();
        TreeMap<String, Object> jsonMap = JSON.parseObject(json,
                new TypeReference<TreeMap<String, Object>>() {
                });

        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

            String key = entry.getKey();
            String strvalue = "";

            if ("sig".equalsIgnoreCase(key)) {
                continue;
            }

            if (entry.getValue() instanceof JSONObject
                    || entry.getValue() instanceof JSONArray) {
                strvalue = getKeyValue(entry.getValue());
            } else {
                strvalue = entry.getValue().toString();
            }

            buffer.append(String.format("%s=%s", key, strvalue));
        }

        buffer.append(secretkey);
        buffer.append("2016");

        String sig = null;
//		LoggerUtils.debug("json value:" + buffer.toString());
//		System.out.println("json value:" + buffer.toString());
        sig = Md5Utils.getStringMD5(buffer.toString());
//		LoggerUtils.debug("sig:" + sig);
        return sig;
    }

    private static String getKeyValue(Object object) {
        StringBuffer buffer = new StringBuffer();
        TreeMap<String, Object> jsonMap = JSON.parseObject(
                JSON.toJSONString(object),
                new TypeReference<TreeMap<String, Object>>() {
                });
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            String key = entry.getKey();
            String strvalue = "";
            if (entry.getValue() instanceof JSONObject || entry.getValue() instanceof JSONArray) {
                strvalue = getKeyValue(entry.getValue());
            } else {
                strvalue = entry.getValue().toString();
            }

            buffer.append(String.format("%s=%s", key, strvalue));
        }
        return buffer.toString();
    }


    private static class Md5Utils {
        /**
         * 获取String的md5值，异常时返回null
         *
         * @param string
         * @return
         */
        public static String getStringMD5(String string) {
            try {
                byte[] hash;
                try {
                    hash = MessageDigest.getInstance("MD5").digest(
                            string.getBytes("UTF-8"));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException("Huh, MD5 should be supported?", e);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("Huh, UTF-8 should be supported?", e);
                }

                StringBuilder hex = new StringBuilder(hash.length * 2);
                for (byte b : hash) {
                    if ((b & 0xFF) < 0x10)
                        hex.append("0");
                    hex.append(Integer.toHexString(b & 0xFF));
                }
                return hex.toString();
            } catch (Exception e) {
                // Logging.error(e.getMessage());
                return null;
            }
        }
    }
}
