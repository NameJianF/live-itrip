package live.itrip.common.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Feng on 2016/7/15.
 * <p>
 * 1. 文件下载
 */
public class HttpDownloadFile {

    private static final Integer TIMEOUT = 30 * 1000;

    /**
     * 文件下载
     *
     * @param filePath
     * @param url
     * @param fileDownloadObserver
     * @return
     */
    public static Boolean downloadFile(String filePath, String url, FileObserver fileDownloadObserver) {
        try {
            URL httpurl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpurl.openConnection();
            //设置超时间为10秒
            conn.setConnectTimeout(TIMEOUT);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();

            // 保存到文件
            saveFile(filePath, inputStream, fileDownloadObserver);

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * @param filePath
     * @param stream
     * @throws IOException
     */
    private static void saveFile(String filePath, InputStream stream, FileObserver fileDownloadObserver) throws IOException {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        byte[] data = readInputStream(stream, fileDownloadObserver);
        FileOutputStream outStream = new FileOutputStream(file);
        outStream.write(data);
        outStream.close();
    }

    private static byte[] readInputStream(InputStream inStream, FileObserver fileDownloadObserver) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[2048];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;

        long transferred = 0L;

        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);

            transferred += len;
            if (fileDownloadObserver != null) {
                fileDownloadObserver.transferred(transferred);
            }
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
