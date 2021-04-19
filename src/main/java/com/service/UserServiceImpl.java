package com.service;

import com.dao.Query;
import com.entity.User;
import com.util.ResultDemo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private Query query;
    public ResultDemo Query(String username,String password){
        Boolean flag = query.query(username, password) || query.queryAdmin(username, password);
        Boolean f = !(flag && query.query(username, password));
        Boolean k = flag && query.queryAdmin(username, password);
        boolean t=f || k;
        if (flag) {
            if (t) {
                return ResultDemo.ok().meta("message", "管理员").data("username", username).status(402);
            } else {
                return ResultDemo.ok().meta("message", "用户").data("username", username).status(401);
            }
        }
        return ResultDemo.error().meta("message", "用户名或密码错误").data("username", null).status(404);
    }
    public ResultDemo Insert(User user){
        boolean flag1 = query.queryByName(user.getUsername());
        if (flag1) {
            boolean flag = query.insert(user);
            if (flag) {
                JSONObject jsonObject = JSONObject.fromObject(user);
                return ResultDemo.ok().meta("status",200).data("data", jsonObject);
            } else {
                return ResultDemo.error().meta("status",404).message("添加失败!");
            }
        }
        return ResultDemo.error().meta("status", 405).message("用户名已存在!");
    }
}
