package live.itrip.sso.common.exception;

import live.itrip.common.Logger;

/**
 * Created by Feng on 2016/3/15.
 * <p>
 * api 异常处理
 */
public class ApiException extends Exception {
    public ApiException(String message, Throwable cause) {
        super(message, cause);
        handleException(false);
    }

    public ApiException(String message, Throwable cause, boolean sendemail) {
        super(message, cause);
        handleException(sendemail);
    }

    private void handleException(boolean sendemail) {
        if (sendemail) {
            // TODO send email

        }
        // logger
        Logger.error(this.getMessage(), this.getCause());
    }
}
