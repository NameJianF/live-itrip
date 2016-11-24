package live.itrip.common.file;


import live.itrip.common.Encoding;

import java.io.File;
import java.io.IOException;

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
    public static File saveFile(String fileName, byte[] data) throws IOException {
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data);
        return file;
    }
}
