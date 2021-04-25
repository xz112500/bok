package com.service;

import com.pojo.dbOrder;
import com.util.ResultDemo;

public interface commService {
    ResultDemo query(int num,int page,String search);
    ResultDemo order(dbOrder dbOrder);
    ResultDemo queryOrder(String username);
}
