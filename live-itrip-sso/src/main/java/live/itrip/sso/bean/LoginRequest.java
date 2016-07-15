package live.itrip.sso.bean;

/**
 * 登录请求
 *
 * @author JianF
 */
public class LoginRequest {
    public String op; //
    public String apikey; // 第三方APP的唯一标识
    public Long timestamp; // 时间戳
    public String sig; // 数字签名


    public LoginData data;

    public LoginRequest() {

    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public static class LoginData {
        public String email;// 用户帐号
        public String password;// 用户密码
        public String source;// 来源包括第三方
        public String uid;   // 第三方登录的uid
        public String clientVersion; // 客户端版本

        public LoginData() {
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getClientVersion() {
            return clientVersion;
        }

        public void setClientVersion(String clientVersion) {
            this.clientVersion = clientVersion;
        }
    }
}
