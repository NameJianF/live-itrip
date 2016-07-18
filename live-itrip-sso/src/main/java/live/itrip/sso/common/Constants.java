package live.itrip.sso.common;

/**
 * 常量
 */
public class Constants {
    public static final String DES_KEY = "feng2016";


    /**
     * session 中存储的用户信息标识
     */
    public static final String SESSION_USER = "SsoSessionUser";

    /**
     * api-log
     */
    public static final String LOG_TAG = "sso-log";

    /**
     * token 过期时间
     */
    public static final Long TOKEN_EXPIRE_TIME = 1000 * 60 * 5L;

    /**
     * 正常登录
     */
    public static final String NORMAL = "normal";

    /**
     * 微信
     */
    public static final String WEICHAT = "weichat";


}
