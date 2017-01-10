package live.itrip.admin.shiro;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.model.AdminRole;
import live.itrip.admin.model.AdminUser;
import live.itrip.admin.model.AdminUserPermission;
import live.itrip.admin.model.User;
import live.itrip.admin.service.intefaces.IAdminUserPermissionService;
import live.itrip.admin.service.intefaces.IAdminUserRoleService;
import live.itrip.admin.service.intefaces.IAdminUserService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Feng on 2016/7/29.
 * <p>
 * 用户身份验证,授权 Realm 组件
 */
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {
    @Autowired
    private IAdminUserRoleService iAdminUserRoleService;

    @Autowired
    private IAdminUserPermissionService iAdminUserPermissionService;

    @Autowired
    private IAdminUserService iAdminUserService;


    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        iTripAdminAuthorizationInfo authorizationInfo = new iTripAdminAuthorizationInfo();
        AdminUser user = (AdminUser) principals.fromRealm(getName()).iterator().next();

        // 查询角色
        final List<AdminRole> roleInfos = iAdminUserRoleService.selectRolesByUserId(user.getId());

        if (roleInfos != null) {
            // 添加角色
            for (AdminRole role : roleInfos) {
                authorizationInfo.addRole(role.getRoleName());
            }

            final List<AdminUserPermission> permissions = iAdminUserPermissionService.selectUserPermissionsByUserId(user.getId());

            if (permissions != null) {
                for (AdminUserPermission permission : permissions) {
                    // 添加权限
                    String tmp = String.format("%s:%s", permission.getModuleId(), permission.getOperation());
                    authorizationInfo.addStringPermission(tmp);
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        BaseResult result = new BaseResult();
        // sso 获取用户信息
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());

        String json = iAdminUserService.login(username, password);
        LoginResponse loginResponse = JSON.parseObject(json, LoginResponse.class);


        if (loginResponse.getCode() != null && loginResponse.getCode() == 0) {
            // 校验账号密码
            User user = loginResponse.getData();
            if (user == null) {
                // 用户名错误
                result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
                result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
                throw new BaseResultAuthenticationException(result);
            } else {
                // 登录成功，异步保存用户信息到本地用户表
                AdminUser localUser = iAdminUserService.selectAdminUserById(user.getId());
                if (localUser == null) {
                    // 本地无该用户信息，保存信息到本地
                    localUser = iAdminUserService.saveUserInfo(user);
                }
                if (localUser != null) {
                    localUser.setToken(user.getToken());

                    return new SimpleAuthenticationInfo(localUser, password, getName());
                }

                // 本地操作异常
                result.setCode(ErrorCode.UNKNOWN.getCode());
                result.setMsg(ErrorCode.UNKNOWN.getMessage());
                throw new BaseResultAuthenticationException(result);
            }
        } else {
            // 用户名错误
            result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
            result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
            throw new BaseResultAuthenticationException(result);
        }
    }

}


/**
 * Created by Feng on 2016/7/15.
 */
class LoginResponse {
    private String op;
    private Integer code;
    private String msg;
    private User data;

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

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
