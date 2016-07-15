package live.itrip.common.http;

import live.itrip.common.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.LinkedList;
import java.util.ListIterator;

public class HttpUploadFile {

    /**
     * @param postUrl
     * @param json
     * @param filePath
     * @param fileUploadObserver
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static String uploadFile(String postUrl, String json, String filePath, FileObserver fileUploadObserver)
            throws NoSuchAlgorithmException, KeyManagementException,
            IOException {

        if (postUrl.startsWith("https")) {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        }

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        URL url = new URL(postUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        // if (Constants.PROXY_WEB_USE) {
        // // 设置代理
        // Properties prop = System.getProperties();
        // // 设置http访问要使用的代理服务器的地址
        // prop.setProperty("http.proxyHost", Constants.PROXY_WEB_IP);
        // // 设置http访问要使用的代理服务器的端口
        // prop.setProperty("http.proxyPort",
        // String.valueOf(Constants.PROXY_WEB_PORT));
        // // 设置http访问要使用的代理服务器的用户名
        // prop.setProperty("http.proxyUser", Constants.PROXY_WEB_USERNAME);
        // // 设置http访问要使用的代理服务器的密码
        // prop.setProperty("http.proxyPassword", Constants.PROXY_WEB_PASSWORD);
        // // 设置安全访问使用的代理服务器地址与端口
        // // 它没有https.nonProxyHosts属性，它按照http.nonProxyHosts 中设置的规则访问
        // prop.setProperty("https.proxyHost", Constants.PROXY_WEB_IP);
        // prop.setProperty("https.proxyPort",
        // String.valueOf(Constants.PROXY_WEB_PORT));
        //
        // }

        httpConn.setConnectTimeout(300000);
        httpConn.setReadTimeout(300000);
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);

        httpConn.setRequestMethod("POST");
        httpConn.setChunkedStreamingMode(1024);

        httpConn.setRequestProperty("UPLOAD-JSON", json);
        httpConn.setRequestProperty("accept", "*/*");

        out = new BufferedOutputStream(httpConn.getOutputStream());
        byte[] buffer = new byte[1024];
        int insize = 0;
        long transferred = 0L;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            do {
                insize = fis.read(buffer);
                if (insize == -1)
                    break;
                out.write(buffer, 0, insize);

                transferred += insize;
                if (fileUploadObserver != null) {
                    fileUploadObserver.transferred(transferred);
                }
            } while (true);

            out.flush();
            out.close();
            out = null;

            in = new BufferedInputStream(httpConn.getInputStream());
            String result = readByteStream(in, "UTF-8");
            return result;

        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException ex) {
                Logger.error("", ex);
            }
        }
    }

    protected static String readByteStream(BufferedInputStream in, String encode)
            throws IOException {
        LinkedList<mybuf> bufList = new LinkedList<mybuf>();
        int size = 0;
        byte buf[];
        do {
            buf = new byte[128];
            int num = in.read(buf);
            if (num == -1)
                break;
            size += num;
            bufList.add(new mybuf(buf, num));
        } while (true);
        buf = new byte[size];
        int pos = 0;
        for (ListIterator<mybuf> p = bufList.listIterator(); p.hasNext(); ) {
            mybuf b = p.next();
            int i = 0;
            while (i < b.size) {
                buf[pos] = b.buf[i];
                i++;
                pos++;
            }
        }
        return new String(buf, encode);
    }

    private static class mybuf {

        public mybuf(byte b[], int s) {
            buf = b;
            size = s;
        }

        public byte buf[];
        public int size;
    }

    private static TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws CertificateException {
        }
    }};
}


