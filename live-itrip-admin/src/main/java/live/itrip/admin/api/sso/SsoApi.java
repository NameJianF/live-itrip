package live.itrip.admin.api.sso;

import live.itrip.admin.api.AbstractApi;

/**
 * Created by Feng on 2016/7/14.
 */
public class SsoApi extends AbstractApi {
    private static final String USER_ACTION = "user.action";

    public String userLogin() {

        return postJsonString(new Object(), USER_ACTION);
    }
}
