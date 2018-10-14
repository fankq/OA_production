package ssm.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import ssm.model.auto.UserInfo;

/**
 * Created by 18510 on 2018/10/10.
 */
public class AccountAuthenticationInfo extends SimpleAuthenticationInfo {
    public AccountAuthenticationInfo(){
    }

    private UserInfo user;

    public AccountAuthenticationInfo(Object principal, Object credentials, String realmName){
        super(principal, credentials, realmName);
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
