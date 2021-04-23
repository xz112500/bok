package com.service;

import com.dao.querycommodityImpl;
import com.pojo.Order;
import com.pojo.VO.Userorder;
import com.util.ResultDemo;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class commServiceImpl implements commService{
    @Autowired
    querycommodityImpl querycommodity;
    @Override
    public ResultDemo query(int num,int page,String search) {
        JSONArray jsonArray=JSONArray.fromObject(querycommodity.query(num,page));
        JSONArray jsonArray1=JSONArray.fromObject(querycommodity.search(search));
        if (querycommodity.search(search) == null){
            return ResultDemo.ok().meta("data",jsonArray).status(409);
        }
       return search.isEmpty() ?  ResultDemo.ok().meta("data",jsonArray) : ResultDemo.ok().meta("data",jsonArray1).status(410);
    }

    @Override
    public ResultDemo order(Order order) {
        List<Order> orders=querycommodity.insert(order);
        List<Userorder> userorders=new ArrayList<>();
       if (orders != null && orders.size() == 1){
           Userorder userorder=new Userorder();
           BeanUtils.copyProperties(orders.get(0),userorder);
           userorders.add(userorder);
           JSONArray jsonArray=JSONArray.fromObject(userorders);
           return userorders.size()>0 ?  ResultDemo.ok().meta("data",jsonArray) : null;
       }
        return ResultDemo.error().status(201);
    }
}
