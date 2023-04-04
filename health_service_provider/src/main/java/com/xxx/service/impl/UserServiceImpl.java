package com.xxx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xxx.dao.PermissionDao;
import com.xxx.dao.RoleDao;
import com.xxx.dao.UserDao;
import com.xxx.pojo.Permission;
import com.xxx.pojo.Role;
import com.xxx.pojo.User;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 用户服务
 */

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User FindByUsername(String username) {
        User user = userDao.FindByUsername(username);
        if (user == null){
            return null;
        }
        Integer userId = user.getId();

        Set<Role> roles = roleDao.findByUserId(userId);

        for (Role role : roles) {
            Integer roleId = role.getId();
            //根据角色id查询关联的权限
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            role.setPermissions(permissions);//让角色关联权限
        }
        user.setRoles(roles);///让用户关联权限
        return user;
    }
}
