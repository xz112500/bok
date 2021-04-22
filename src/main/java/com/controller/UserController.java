package com.controller;

import com.dao.Query;
import com.pojo.VO.Comment;
import com.pojo.dbUser;
import com.service.UserServiceImpl;
import com.util.ResultDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class UserController {
    @Autowired
    Query query;
    @Autowired
    UserServiceImpl userServiceImpl;
    @RequestMapping(value = "/user/authority", method = RequestMethod.POST)
    public ResultDemo Query(String username, String password) {
        return userServiceImpl.Query(username,password);
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResultDemo insert(@RequestBody dbUser user) {
       return userServiceImpl.Insert(user);
    }

    @RequestMapping(value = "/user/comment",method = RequestMethod.POST)
    public ResultDemo comment(@RequestBody Comment comment){
        return userServiceImpl.comment(comment);
    }
}
