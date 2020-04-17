package com.xichoo.finax.modules.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xichoo.finax.common.util.Constant;
import com.xichoo.finax.modules.system.entity.Menu;
import com.xichoo.finax.modules.system.entity.User;
import com.xichoo.finax.modules.system.service.MenuService;
import com.xichoo.finax.modules.system.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro认证
 * @author xichoo@live.cn
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * 权限验证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<>();
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Menu> menuList = menuService.getListByUserid(user.getId(), Constant.MenuType.THIRD.getType());

        for(Menu menu : menuList){
            if(Strings.isBlank(menu.getPermission())){
                continue;
            }
            permissions.add(menu.getPermission());
        }
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if(user == null){
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getUsername()), getName());
    }

    /**
     * 加密规则
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(Constant.SHIRO_ALGORITHMNAME);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
