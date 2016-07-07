package live.itrip.common.util;

import live.itrip.common.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Feng on 2016/7/7.
 * 序列化工具
 */
public class SerializeUtils {
    /**
     * @param object
     * @return
     * @throws Exception
     * @Description 序列化
     */
    public static byte[] serialize(Object object) throws Exception {
        if (object == null) return null;
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            Logger.error("Serialize Error:" + e);
            throw e;
        }
    }

    /**
     * @param bytes
     * @return
     * @throws Exception
     * @Description 反序列化
     */
    public static Object unSerialize(byte[] bytes) throws Exception {
        if (bytes == null) return null;
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            Logger.error("UnSerialize Error:" + e);
            throw e;
        }
    }
}
