package com.service;

import com.entity.User;
import com.util.ResultDemo;

public interface UserService {
    ResultDemo Query(String username, String password);
    ResultDemo Insert(User user);
}
