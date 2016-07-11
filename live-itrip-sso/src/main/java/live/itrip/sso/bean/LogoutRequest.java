package live.itrip.sso.bean;

import live.itrip.common.request.RequestHeader;

/**
 * Created by Feng on 2016/3/15.
 * <p>
 * logout
 */
public class LogoutRequest extends RequestHeader {
    private Integer uid;   // 用户uid
    private String email; // email


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
