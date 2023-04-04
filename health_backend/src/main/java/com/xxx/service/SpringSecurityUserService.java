package com.xxx.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xxx.pojo.Permission;
import com.xxx.pojo.Role;
import com.xxx.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference
    private UserService userService;

    //根据用户名查询数据库获取用户信息
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.FindByUsername(username);
        if (user == null) {
            //用户名不存在
            return null;
        }
        List<GrantedAuthority> list = new ArrayList<>();
        //动态授权
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            //遍历角色集合为用户授予角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions(); 
            for (Permission permission : permissions) {
                //遍历权限集合为用户授权
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
        return securityUser;
    }
}
