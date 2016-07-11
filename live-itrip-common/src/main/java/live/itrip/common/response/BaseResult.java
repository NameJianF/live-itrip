package live.itrip.common.response;

/**
 * Created by 建锋 on 2016/7/7.
 * <p>
 * response simple result
 */
public class BaseResult {
    private String op;
    private Integer code;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
