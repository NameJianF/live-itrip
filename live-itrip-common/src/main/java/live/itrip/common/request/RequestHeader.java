package live.itrip.common.request;

/**
 * Created by 建锋 on 2016/7/7.
 */
public class RequestHeader {
    public String op; //
    public String apikey; // 唯一标识
    public String sid; // sid值
    public Long timestamp; // 时间戳
    public String sig; // 数字签名

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }
}
