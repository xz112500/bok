package com.dao;

import com.pojo.VO.Comment;
import com.pojo.dbUser;

import java.util.List;

public interface Query {
     List<dbUser> query(String username, String password);

     dbUser insert(dbUser user);

     List<dbUser> queryByName(String username);

     Comment comment(Comment comment);
}
