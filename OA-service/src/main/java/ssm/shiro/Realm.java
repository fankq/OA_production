package ssm.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import ssm.model.auto.UserInfo;
import ssm.service.HrmService;

import java.util.List;

/**
 * Created by 18510 on 2018/10/10.
 */
public class Realm extends AuthorizingRealm {

    @Autowired
    HrmService hrmService;
    /**


     * 为当前登录的Subject授予角色和权限
     * @see  经测试:本例中该方法的调用时机为需授权资源被访问时

     * @see  经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache

     * @see  个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache

     * @see  比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
    * 认证通过之后进行授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       String username = (String) principalCollection.fromRealm(getName()).iterator().next();
       /* if (username != null) {
            UserVO user = userService.getByUsername(username);
            if (user != null) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                List<Role> roles = userRoleService.listRoles(user.getId());

                //赋予角色
                roles.forEach(role -> {
                    info.addRole(role.getName());

                    //赋予权限
                    role.getPermissions().forEach(permission -> info.addStringPermission(permission.getName()));
                });
                return info;
            }
        }*/
        if(username!=null){
            UserInfo user =hrmService.findUserInfoByUsername(username);
            if (user != null) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                info.addStringPermission("1111");
                info.addRole("edit");
                return info;
            }
        }
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;

        //校验用户名密码
        UserInfo user =hrmService.findUserInfoByUsername(upToken.getUsername());
        AccountAuthenticationInfo info = new AccountAuthenticationInfo(upToken.getPrincipal(), upToken.getCredentials(), getName());

        if(user!=null){
            if(String.valueOf(upToken.getPassword()).equals(user.getPassword())){
                info.setUser(user);
            }else{
                throw new AuthenticationException("密码不正确");
            }
        }else{
            throw new UnknownAccountException("该用户信息不存在");
        }
        return info;
    }

    /**

     * 将一些数据放到ShiroSession中,以便于其它地方使用

     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到

     */

    private void setSession(Object key, Object value){

        Subject currentUser = SecurityUtils.getSubject();

        if(null != currentUser){

            Session session = currentUser.getSession();

            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");

            if(null != session){

                session.setAttribute(key, value);

            }

        }

    }

}
