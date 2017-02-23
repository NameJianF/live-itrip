package live.itrip.sso.rpc.service;

/**
 * Created by Feng on 2016/3/8.
 */
public interface RpcSsoService {

    /**
     * return  json String
     *
     * @param email
     * @param password
     * @param apikey
     * @param source
     * @param host
     * @param clientVersion
     * @return
     */
    String login(String email, String password, String apikey, String source, String host, String clientVersion);


}
