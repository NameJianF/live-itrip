package live.itrip.admin.model;

public class User extends BaseModel{

    private String userName;

    private String email;

    private String mobile;

    private String password;

    private String salt;

    private String uidQq;

    private String uidWeibo;

    private String uidWeixin;

    private String uidAli;

    private String level;

    private String status;

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

}