package live.itrip.admin.common;

/**
 * Created by 建锋 on 2016/7/7.
 */
public class Constants {

    public static final String DES_KEY = "feng2016";

    /**
     * session 中存储的用户信息标识
     */
    public static final String SESSION_USER = "SessionUserInfo";

    public static final String COOKIE_TOKEN_NAME = "userToken";

    /**
     * 正常登录
     */
    public static final String NORMAL = "normal";

    /**
     * 微信
     */
    public static final String WEICHAT = "weichat";


    // ============== flag =========
    /**
     * 删除标记
     */
    public static final String FLAG_IS_DELETE = "1";
    /**
     * 未删除标记
     */
    public static final String FLAG_NO_DELETE = "0";
    /**
     * 可用标记
     */
    public static final String FLAG_AVAILABLE = "1";
    /**
     * 不可用标记
     */
    public static final String FLAG_UNAVAILABLE = "0";


    public static class Sex {
        public static final String MAN = "1";
        public static final String WOMEN = "0";
        public static final String UNKNOWN = "-1";
    }

    public static class RoleName {
        public static final String ADMINISTRATOR = "administrator";
        public static final String ADMIN = "manager";
    }
}
