package live.itrip.web.api.sso.bean;

import live.itrip.web.model.AdminUser;

/**
 * Created by Feng on 2016/7/15.
 */
public class LoginResponse {
    private String op;
    private Integer code;
    private String msg;
    private AdminUser data;

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

    public AdminUser getData() {
        return data;
    }

    public void setData(AdminUser data) {
        this.data = data;
    }
}
