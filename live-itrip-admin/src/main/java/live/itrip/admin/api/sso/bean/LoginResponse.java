package live.itrip.admin.api.sso.bean;

/**
 * Created by Feng on 2016/7/15.
 */
public class LoginResponse {
    private String op;
    private Integer code;
    private String msg;
    private User data;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
