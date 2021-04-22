package com.service;

import com.pojo.VO.Comment;
import com.pojo.dbUser;
import com.util.ResultDemo;

public interface UserService {
    ResultDemo Query(String username, String password);
    ResultDemo Insert(dbUser user);
    ResultDemo comment(Comment comment);
}
