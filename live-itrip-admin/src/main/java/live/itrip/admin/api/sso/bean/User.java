package live.itrip.admin.api.sso.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Feng on 2016/7/15.
 */
public class User implements Serializable {
    private Long id;

    private String userName;

    private String email;

    private String mobile;

    private String password;

    private String salt;

    private String uidQq;

    private String uidWeibo;

    private String uidWeixin;

    private String uidAli;

    private String source;

    private String subsource;

    private String level;

    private String status;

    private String identity;

    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getUidQq() {
        return uidQq;
    }

    public void setUidQq(String uidQq) {
        this.uidQq = uidQq == null ? null : uidQq.trim();
    }

    public String getUidWeibo() {
        return uidWeibo;
    }

    public void setUidWeibo(String uidWeibo) {
        this.uidWeibo = uidWeibo == null ? null : uidWeibo.trim();
    }

    public String getUidWeixin() {
        return uidWeixin;
    }

    public void setUidWeixin(String uidWeixin) {
        this.uidWeixin = uidWeixin == null ? null : uidWeixin.trim();
    }

    public String getUidAli() {
        return uidAli;
    }

    public void setUidAli(String uidAli) {
        this.uidAli = uidAli == null ? null : uidAli.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getSubsource() {
        return subsource;
    }

    public void setSubsource(String subsource) {
        this.subsource = subsource == null ? null : subsource.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
