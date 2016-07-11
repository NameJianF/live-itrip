package live.itrip.sso.bean;

import live.itrip.common.request.RequestHeader;

/**
 * Created by Feng on 2016/3/15.
 */
public class AuthUserRequest extends RequestHeader {
    private String email; // email
    private String mobile; // mobile
    private String sid; // token

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getSid() {
        return sid;
    }

    @Override
    public void setSid(String sid) {
        this.sid = sid;
    }
}
