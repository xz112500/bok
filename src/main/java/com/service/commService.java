package com.service;

import com.pojo.Order;
import com.pojo.VO.Userorder;
import com.util.ResultDemo;
import java.util.List;

public interface commService {
    ResultDemo query(int num,int page,String search);
    ResultDemo order(Order order);
}
