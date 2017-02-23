package live.itrip.web.shiro;

import com.alibaba.fastjson.JSON;
import live.itrip.web.api.sso.SsoApi;
import live.itrip.web.api.sso.bean.LoginResponse;
import live.itrip.web.model.AdminRole;
import live.itrip.web.model.AdminUser;
import live.itrip.web.model.AdminUserPermission;
import live.itrip.web.service.intefaces.IAdminUserPermissionService;
import live.itrip.web.service.intefaces.IAdminUserRoleService;
import live.itrip.web.service.intefaces.IAdminUserService;
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
 * <p/>
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

        LoginResponse loginResponse = new SsoApi().userLogin(username, password);

        if (loginResponse.getCode() != null && loginResponse.getCode() == 0) {
            // 校验账号密码
            AdminUser user = loginResponse.getData();
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


