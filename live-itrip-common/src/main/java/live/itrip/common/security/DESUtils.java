package live.itrip.common.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * des 加密/解密
 *
 * @author 建锋
 */
public class DESUtils {
    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};

    /**
     * 加密
     *
     * @param encryptString 明文
     * @param encryptKey    密钥，长度>=8
     * @return 密文
     * @throws Exception
     */
    public static String encryptDES(String encryptString, String encryptKey)
            throws Exception {
        // IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return Base64Utils.encode(encryptedData);
    }

    /**
     * 解密
     *
     * @param decryptString 密文
     * @param decryptKey    密钥，长度>=8
     * @return 明文
     * @throws Exception
     */
    public static String decryptDES(String decryptString, String decryptKey)
            throws Exception {
        byte[] byteMi = Base64Utils.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        // IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData);
    }


    public static void main(String[] args) throws Exception {


        String ss = "701917a6eca24a14b9246bb331e144d8";
        System.out.println("加密前的字符串:" + ss);
        String dd = DESUtils.encryptDES(ss, "fengfeng");
        System.err.println("加密后:" + dd);

        String srcBytes = DESUtils.decryptDES(dd, "fengfeng");
        System.out.println("解密后的字符串:" + srcBytes);
    }
}
