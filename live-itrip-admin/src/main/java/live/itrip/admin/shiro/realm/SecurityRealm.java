package live.itrip.admin.shiro.realm;

import live.itrip.admin.api.sso.bean.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * Created by Feng on 2016/7/28.
 */
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    /**
     * 权限检查
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        String username = String.valueOf(principals.getPrimaryPrincipal());
//
//        final User user = userService.selectByUsername(username);
//        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
//        for (Role role : roleInfos) {
//            // 添加角色
//            System.err.println(role);
//            authorizationInfo.addRole(role.getRoleSign());
//
//            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
//            for (Permission permission : permissions) {
//                // 添加权限
//                System.err.println(permission);
//                authorizationInfo.addStringPermission(permission.getPermissionSign());
//            }
//        }
//        return authorizationInfo;
        return null;
    }

    /**
     * 登录验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username = String.valueOf(authenticationToken.getPrincipal());
//        String password = new String((char[]) authenticationToken.getCredentials());
//        // 通过数据库进行验证
//        final User authentication = userService.authentication(new User(username, password));
//        if (authentication == null) {
//            throw new AuthenticationException("用户名或密码错误.");
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
//        return authenticationInfo;
        return null;
    }
}
