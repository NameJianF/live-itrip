package live.itrip.sso.interfaces;

import live.itrip.common.request.RequestHeader;
import live.itrip.common.response.BaseResult;

/**
 * 验证sig和token
 *
 * @author JianF
 */
public interface IValidateParams {
    BaseResult validateParams(RequestHeader header);
}
