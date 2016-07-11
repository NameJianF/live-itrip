package live.itrip.common.util;

import java.util.UUID;

public class UuidUtils {

	/**
	 * 获取 uuid
	 * 
	 * @param contain
	 *            是否包含 "-"
	 * @return
	 */
	public static String getUuidLowerCase(boolean contain) {
		UUID uuid = UUID.randomUUID();
		if (contain) {
			return uuid.toString().toLowerCase();
		}
		return uuid.toString().replace("-", "").toLowerCase();
	}

	/**
	 * 获取 uuid
	 * 
	 * @param contain
	 *            是否包含 "-"
	 * @return
	 */
	public static String getUuidUpperCase(boolean contain) {
		UUID uuid = UUID.randomUUID();
		if (contain) {
			return uuid.toString().toUpperCase();
		}
		return uuid.toString().replace("-", "").toUpperCase();
	}
}
