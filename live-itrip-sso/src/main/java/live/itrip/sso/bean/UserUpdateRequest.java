package live.itrip.sso.bean;

import live.itrip.common.request.RequestHeader;
import live.itrip.sso.model.User;

/**
 * Created by Feng on 2016/3/15.
 * <p>
 * 修改用户信息
 */
public class UserUpdateRequest extends RequestHeader {
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
