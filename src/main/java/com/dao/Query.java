package com.dao;

import com.entity.User;
import com.util.Rule;

import java.util.List;

public interface Query {
     Boolean query(String username,String password);

     Boolean queryAdmin(String username,String password);

     Boolean insert(User user);

     Boolean queryByName(String username);
}
