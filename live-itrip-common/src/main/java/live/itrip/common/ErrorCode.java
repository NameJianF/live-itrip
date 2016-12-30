package live.itrip.common;

/**
 * Created by Feng on 2016/7/7.
 * <p>
 * 错误码列表
 */
public enum ErrorCode {

    SUCCESS(0, "success"),
    UNKNOWN(-1, "未知错误"),
    PARAM_INVALID(1000, "参数无效"),
    SERVICE_INITING(9999, "系统初始化中..."),

    // ========= 100---200 用户相关错误码
    USERNAME_PWD_INVALID(100, "用户名/密码错误"),
    CAPTCHA_INVALID(101, "验证码错误"),
    USER_INVALID(102, "该用户无效"),
    USER_AUTH_INVALID(103, "用户未认证"),
    USER_AUTH_EXIST(104,"该用户已存在");

    private Integer code;
    private String message;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
