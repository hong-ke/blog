package com.hk.blog.service;


import com.hk.blog.entity.User;

public interface UserService {



    User login(User user);

    void register(User user);
}
