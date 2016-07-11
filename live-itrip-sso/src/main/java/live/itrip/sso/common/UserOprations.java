package live.itrip.sso.common;

public class UserOprations {

    /**
     * 定义 api 对外op操作列表
     */
    // --------------------- user -----------

    /**
     * 注册
     */
    public static final String OP_API_USER_REGISTER = "User.register";


    /**
     * 用户信息修改
     */
    public static final String OP_API_USER_UPDATE_INFO = "User.updateInfo";

    /**
     * 修改用户密码
     */
    public static final String OP_API_USER_UPDATE_PWD = "User.updatePassword";

    /**
     * 找回密码
     */
    public static final String OP_API_USER_RETRIEVE_PWD = "User.retrieve";

}
