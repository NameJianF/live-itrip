package live.itrip.admin.bean;

/**
 * Created by Feng on 2016/10/12.
 */
public class WebLoginData {
    private String userName;
    private String pwd;
    private String captcha = "";


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
