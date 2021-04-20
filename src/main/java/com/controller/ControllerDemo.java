package com.controller;

import com.dao.Query;
import com.entity.Comment;
import com.entity.User;
import com.service.UserServiceImpl;
import com.util.Gettoken;
import com.util.ResultDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class ControllerDemo {
    @Autowired
    Query query;
    @Autowired
    UserServiceImpl userServiceImpl;
    @RequestMapping(value = "/user/authority", method = RequestMethod.POST)
    public ResultDemo Query(String username, String password) {
        return userServiceImpl.Query(username,password);
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResultDemo insert(@RequestBody User user) {
       return userServiceImpl.Insert(user);
    }

    @RequestMapping(value = "/user/comment",method = RequestMethod.POST)
    public ResultDemo comment(@RequestBody Comment comment){
        return userServiceImpl.comment(comment);
    }
}
