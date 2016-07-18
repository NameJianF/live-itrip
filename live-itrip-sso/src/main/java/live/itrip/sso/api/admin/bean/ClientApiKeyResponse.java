package live.itrip.sso.api.admin.bean;

import java.util.List;

/**
 * Created by Feng on 2016/7/18.
 */
public class ClientApiKeyResponse {

    private String op;
    private Integer code;
    private String msg;
    private List<ClientApiKey> data;

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

    public List<ClientApiKey> getData() {
        return data;
    }

    public void setData(List<ClientApiKey> data) {
        this.data = data;
    }
}
