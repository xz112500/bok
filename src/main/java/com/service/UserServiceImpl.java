package com.service;

import com.dao.Query;
import com.pojo.VO.Comment;
import com.pojo.VO.User;
import com.pojo.dbUser;
import com.util.Gettoken;
import com.util.ResultDemo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    public static Gettoken gettoken=new Gettoken();
    @Autowired
    private Query query;
    public ResultDemo Query(String username,String password) {
        List<dbUser> list= query.queryByName(username);
        if (list.size()>0){
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                String encodePass=password+username+"fine";//加密密码
                String encodingPass = DigestUtils.md5DigestAsHex(encodePass.getBytes());
                if (encodingPass.equals(list.get(0).getPassword()) && username.equals(list.get(0).getUsername())) {
                    return ResultDemo.ok().meta("message", "用户").data("username", username).status(401).data("token", list.get(0).getToken());//返回token
                } else {
                    return ResultDemo.error().meta("message", "用户名或密码错误").data("username", null).status(404);
                }
            }return ResultDemo.error().meta("message","未知错误!").status(403);
        }return ResultDemo.error().meta("message","请先注册!").status(405);

    }
    public ResultDemo Insert(dbUser user){
        boolean flag = query.queryByName(user.getUsername()).size()>0;
        if (!flag) {
            if ((query.insert(user) != null)) {
                List<dbUser> list=query.queryByName(user.getUsername());

                User user1=new User();

                BeanUtils.copyProperties(list.get(0),user1);

                JSONObject jsonObject = JSONObject.fromObject(user1);
                return ResultDemo.ok().meta("status",200).data("data",jsonObject);
            } else {
                return ResultDemo.error().meta("status",404).message("添加失败!");
            }
        }return ResultDemo.error().meta("status", 405).message("用户名已存在!");
    }
    public ResultDemo comment(Comment comment){
       Comment comment1= query.comment(comment);
       return ResultDemo.ok().meta("data",JSONObject.fromObject(comment1)).meta("message","评论成功!");
    }
}
