package com.service;

import com.dao.Query;
import com.entity.Comment;
import com.entity.User;
import com.util.Gettoken;
import com.util.ResultDemo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    public static Gettoken gettoken=new Gettoken();
    @Autowired
    private Query query;
    public ResultDemo Query(String username,String password){
        Boolean flag = query.query(username, password) || query.queryAdmin(username, password);
        Boolean flag1 = !(flag && query.query(username, password));
        Boolean flag2 = flag && query.queryAdmin(username, password);
        boolean t=flag1 || flag2;
        if (flag) {
            if (t) {
                return ResultDemo.ok().meta("message", "管理员").data("username", username).status(402);
            } else {
                return ResultDemo.ok().meta("message", "用户").data("username", username).status(401).data("token",gettoken.gettoken(username,password));
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
    public ResultDemo comment(Comment comment){
       Comment comment1= query.comment(comment);
       return ResultDemo.ok().data("data",JSONObject.fromObject(comment1)).meta("message","评论成功!");
    }
}
