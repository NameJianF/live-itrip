package live.itrip.sso.common;

import live.itrip.sso.api.admin.bean.ClientApiKey;

import java.util.List;

/**
 * Created by Feng on 2016/7/14.
 */
public class Config {

    public static String MODULE_APP_APIKEY;

    public static String MODULE_APP_SECRET;

    public static String ADMIN_URL;

    /**
     * admin 获取到的 apikey 列表
     */
    public static List<ClientApiKey> LIST_APIKEY;

    /**
     * 配置是否加载完成
     *
     * @return
     */
    public static boolean inited() {
        if (MODULE_APP_APIKEY == null
                || MODULE_APP_SECRET == null
                || ADMIN_URL == null
                || LIST_APIKEY == null) {
            return false;
        }
        return true;
    }
}
