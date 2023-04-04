package com.xxx.dao;

import com.xxx.pojo.User;

public interface UserDao {
    public User FindByUsername(String username);
}
