package live.itrip.sso.bean;


import live.itrip.common.request.RequestHeader;

/**
 * Created by Feng on 2016/3/15.
 * <p>
 * 找回密码
 */
public class RetrievePwdRequest extends RequestHeader {
    private String email;
    private String mobile;

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
}
