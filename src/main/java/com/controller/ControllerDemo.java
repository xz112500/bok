package com.controller;

import com.dao.Query;
import com.entity.User;
import com.util.ResultDemo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class ControllerDemo {
    @Autowired
    Query query;
    @RequestMapping(value = "/user/authority", method = RequestMethod.POST)
    public ResultDemo Query(String username, String password) {
        Boolean flag = query.query(username, password) || query.queryAdmin(username, password);
        Boolean f = !(flag && query.query(username, password));
        Boolean k = flag && query.queryAdmin(username, password);
        boolean t = f || k;
        if (flag) {
            if (t) {
                return ResultDemo.ok().meta("message", "管理员").data("username", username).status(402);
            } else {
                return ResultDemo.ok().meta("message", "用户").data("username", username).status(401);
            }
        } return ResultDemo.error().meta("message","用户名或密码错误").data("username",null).status(404);
    }
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public ResultDemo insert(@RequestBody User user){
        boolean flag=query.insert(user);
        JSONObject jsonObject=JSONObject.fromObject(user);
            if (flag){
                return ResultDemo.ok().meta("message","添加成功").data("data",jsonObject);
            }
            return ResultDemo.error().meta("status",404);
    }
}
