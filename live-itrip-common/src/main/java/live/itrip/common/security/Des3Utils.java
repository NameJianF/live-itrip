package live.itrip.common.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 加密工具类
 * 
 * @author Feng
 *
 */
public class Des3Utils {

	private static String STR_KEY = "12345678901234567890123456789000";
	private static byte[] STR_KEY_IV = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static byte[] des3EncodeECB(byte[] data) throws Exception {
		// 密钥
		byte[] key = Base64.decode(STR_KEY, Base64.DEFAULT);

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	public static byte[] des3DecodeECB(byte[] data) throws Exception {
		// 密钥
		byte[] key = Base64.decode(STR_KEY, Base64.DEFAULT);

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;

	}

	public static byte[] des3EncodeCBC(byte[] data) throws Exception {
		// 密钥
		byte[] key = Base64.decode(STR_KEY, Base64.DEFAULT);
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(STR_KEY_IV);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);

		return bOut;
	}

	public static byte[] des3DecodeCBC(byte[] data) throws Exception {
		// 密钥
		byte[] key = Base64.decode(STR_KEY, Base64.DEFAULT);

		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(STR_KEY_IV);

		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] bOut = cipher.doFinal(data);

		return bOut;

	}
}
