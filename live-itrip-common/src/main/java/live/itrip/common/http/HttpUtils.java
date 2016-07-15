package live.itrip.common.http;

import live.itrip.common.Logger;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Feng on 2016/7/15.
 * <p>
 * http 访问网络用到的工具类
 */
public class HttpUtils {
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";

    private static HttpUtils instance;

    public static HttpUtils getInstance() {
        if (instance == null) {
            instance = new HttpUtils();
        }
        return instance;
    }

    /**
     * 发起http请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
        URL url;
        HttpURLConnection connection = null;
        try {
            if (StringUtils.isEmpty(requestMethod)) {
                requestMethod = REQUEST_METHOD_GET;
            }

            // Create connection
            url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(REQUEST_METHOD_POST);
            connection.setRequestProperty("Content-Type",
                    "application/json; charset=UTF-8");

            connection.setRequestProperty("Content-Length",
                    "" + Integer.toString(outputStr.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(outputStr);
            wr.flush();
            wr.close();

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {
            Logger.error("", e);
            return null;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
