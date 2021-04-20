package com.dao;

import com.entity.Comment;
import com.entity.User;

public interface Query {
     Boolean query(String username,String password);

     Boolean queryAdmin(String username,String password);

     Boolean insert(User user);

     Boolean queryByName(String username);

     Comment comment(Comment comment);
}
