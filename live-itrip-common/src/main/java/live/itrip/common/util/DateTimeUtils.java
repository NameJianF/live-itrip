package live.itrip.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式化
 * 
 * @author Feng
 *
 */
public class DateTimeUtils {

	/**
	 * yyyy-MM-dd HH:mm:ss,SSS
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		String result;
		String formatStr = "yyyy-MM-dd HH:mm:ss,SSS";
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		result = sdf.format(new Date());
		return result;
	}

	/**
	 * yyyyMMddhhmmss
	 * 
	 * @return
	 */
	public static String getCurrentDateTime3() {
		String result;
		String formatStr = "yyyyMMddHHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		result = sdf.format(new Date());
		return result;
	}

	/**
	 * yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	public static String getCurrentDateTime4() {
		String result;
		String formatStr = "yyyyMMddHHmmssSSS";
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		result = sdf.format(new Date());
		return result;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentDateTime2() {
		String result;
		String formatStr = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		result = sdf.format(new Date());
		return result;
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		String result;
		String formatStr = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		result = sdf.format(new Date());
		return result;
	}

	/**
	 * HH:mm:ss,SSS
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		String result;
		String formatStr = "HH:mm:ss,SSS";
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		result = sdf.format(new Date());
		return result;
	}
	/**
	 * HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentTime2() {
		String result;
		String formatStr = "HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		result = sdf.format(new Date());
		return result;
	}
	
	/**
	 * 格式化时间戳
	 * @param curTime
	 * @param pattern
	 * @return
	 */
	public static String formatDateTime(Long curTime, String pattern) {
		String result = "";
		if (curTime == null) {
			return result;
		}
		if (pattern == null) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
		try {
			result = sdf.format(curTime);
		} catch (Exception e) {
			return result;
		}
		return result;

	}
}
