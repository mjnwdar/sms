package com.sherlock.example.shiro.realm;

import com.sherlock.example.rpc.LoginService;
import com.sherlock.model.vo.UserVo;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;

/**
 * 根据用户名密码校验的 Realm.
 * @author Sherlock
 */
@Slf4j
public class UserNameRealm extends AuthorizingRealm {
    @Resource
    private LoginService loginService;

    @Resource
    private SessionDAO sessionDAO;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("从数据库获取权限信息");
        UserVo user = (UserVo) principals.getPrimaryPrincipal();
        String userName = user.getUserName();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /**
         * 此处可以加上自己的验证权限的逻辑
         */
        authorizationInfo.setObjectPermissions(Collections.singleton(permission -> true));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("从数据库获取认证信息");
        String userName = (String) token.getPrincipal();
        String password = (String) token.getCredentials();
        UserVo user = loginService.getUserInfo(userName, password);
        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), super.getName());
    }

    public void clearAuthCacheByUserId(Integer userId) {
        // 获取所有 session
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            // 获取 session 登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (obj instanceof SimplePrincipalCollection) {
                // 强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                UserVo user = new UserVo();
                BeanUtils.copyProperties(spc.getPrimaryPrincipal(), user);
                // 判断用户, 匹配用户ID.
                if (userId.equals(user.getUserId())) {
                    this.doClearCache(spc);
                }
            }
        }
    }

    public void clearAllAuthCache() {
        // 获取所有 session
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            // 获取 session 登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (obj instanceof SimplePrincipalCollection) {
                // 强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                UserVo user = new UserVo();
                BeanUtils.copyProperties(spc.getPrimaryPrincipal(), user);
                this.doClearCache(spc);
            }
        }
    }

    /**
     * 超级管理员拥有所有权限
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        UserVo user = (UserVo) principals.getPrimaryPrincipal();
        return super.isPermitted(principals, permission);
    }

    /**
     * 超级管理员拥有所有角色
     */
    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        UserVo user = (UserVo) principals.getPrimaryPrincipal();
        return super.hasRole(principals, roleIdentifier);
    }
}