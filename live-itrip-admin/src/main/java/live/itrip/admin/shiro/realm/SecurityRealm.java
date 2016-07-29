package live.itrip.admin.shiro.realm;

import live.itrip.admin.api.sso.SsoApi;
import live.itrip.admin.api.sso.bean.LoginResponse;
import live.itrip.admin.api.sso.bean.User;
import live.itrip.admin.exception.BaseResultAuthenticationException;
import live.itrip.admin.model.AdminPermission;
import live.itrip.admin.model.AdminRole;
import live.itrip.admin.service.intefaces.IAdminRolePermission;
import live.itrip.admin.service.intefaces.IAdminUserRole;
import live.itrip.admin.service.intefaces.IUserService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
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
    private IAdminUserRole iAdminUserRole;

    @Autowired
    private IAdminRolePermission iAdminRolePermission;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.fromRealm(getName()).iterator().next();

        // 查询角色
        final List<AdminRole> roleInfos = iAdminUserRole.selectRolesByUserId(user.getId());
        for (AdminRole role : roleInfos) {
            // 添加角色
            System.err.println(role);
            authorizationInfo.addRole(role.getRoleSign());

            final List<AdminPermission> permissions = iAdminRolePermission.selectPermissionsByRoleId(role.getId());
            for (AdminPermission permission : permissions) {
                // 添加权限
                System.err.println(permission);
                authorizationInfo.addStringPermission(permission.getPermissionSign());
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

        SsoApi ssoApi = new SsoApi();
        LoginResponse loginResponse = ssoApi.userLogin(username, password);

        if (loginResponse.getCode() != null && loginResponse.getCode() == 0) {
            // 校验账号密码
            User user = loginResponse.getData();
            if (user == null) {
                // 用户名错误
                result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
                result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
                throw new BaseResultAuthenticationException(result);
            } else {
                return new SimpleAuthenticationInfo(user, password, getName());
            }
        } else {
            // 用户名错误
            result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
            result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
            throw new BaseResultAuthenticationException(result);
        }
    }

}