package live.itrip.common.util;


import live.itrip.common.Encoding;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Feng on 2016/7/1.
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * @param fileName
     * @param data
     * @param encoding
     * @throws IOException
     */
    public static void saveFile(String fileName, String data, String encoding) throws IOException {
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        org.apache.commons.io.FileUtils.writeStringToFile(file, data, encoding);
    }

    /**
     * @param fileName
     * @param data
     * @throws IOException
     */
    public static void saveFile(String fileName, String data) throws IOException {
        saveFile(fileName, data, Encoding.UTF8);
    }

    /**
     * @param fileName
     * @param data
     * @throws IOException
     */
    public static void saveFile(String fileName, byte[] data) throws IOException {
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data);
    }

    /**
     * @param fileName
     * @param stream
     * @throws IOException
     */
    public static void saveFile(String fileName, InputStream stream) throws IOException {
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        byte[] data = readInputStream(stream);
        FileOutputStream outStream = new FileOutputStream(file);
        outStream.write(data);
        outStream.close();
    }

    /**
     * @param fileName
     * @param url
     * @return
     * @throws IOException
     */
    public static void saveFileFromUrl(String fileName, String url) throws IOException {
        URL httpurl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) httpurl.openConnection();
        //设置超时间为10秒
        conn.setConnectTimeout(10 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();

        // 保存到文件
        saveFile(fileName, inputStream);
    }


    private static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[2048];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

}
