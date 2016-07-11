package live.itrip.sso.bean;

import live.itrip.common.request.RequestHeader;

/**
 * Created by Feng on 2016/3/15.
 */
public class UserUpdatePwdRequest extends RequestHeader {

    private Long uid;  // user id
    private String originPwd; // old password
    private String password;  // new password

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getOriginPwd() {
        return originPwd;
    }

    public void setOriginPwd(String originPwd) {
        this.originPwd = originPwd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
