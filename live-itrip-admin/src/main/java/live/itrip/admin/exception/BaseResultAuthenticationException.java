package live.itrip.admin.exception;

import live.itrip.common.response.BaseResult;
import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Feng on 2016/7/29.
 */
public class BaseResultAuthenticationException extends AuthenticationException {
    private BaseResult result;

    public BaseResultAuthenticationException() {
    }

    public BaseResultAuthenticationException(String message) {
        super(message);
    }

    public BaseResultAuthenticationException(Throwable cause) {
        super(cause);
    }

    public BaseResultAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseResultAuthenticationException(BaseResult result) {
        this.result = result;
    }

    public BaseResult getResult() {
        return result;
    }

    public void setResult(BaseResult result) {
        this.result = result;
    }
}
