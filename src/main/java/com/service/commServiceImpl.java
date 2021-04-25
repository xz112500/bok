package com.service;

import com.dao.CommodityImpl;
import com.pojo.dbOrder;
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
    CommodityImpl querycommodity;
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
    public ResultDemo order(dbOrder dbOrder) {
        List<dbOrder> dbOrders =querycommodity.insert(dbOrder);
        List<Userorder> userorders=new ArrayList<>();
        int newcount= dbOrder.getAllcount()- dbOrder.getCounts();
         querycommodity.update(newcount, dbOrder.getNumbers());
          if (dbOrders != null && dbOrders.size() == 1){
              Userorder userorder=new Userorder();
              BeanUtils.copyProperties(dbOrders.get(0),userorder);
              userorders.add(userorder);
              JSONArray jsonArray=JSONArray.fromObject(userorders);
              return userorders.size()>0 ?  ResultDemo.ok().meta("data",jsonArray) : null;
          }
          return ResultDemo.error().status(201);
    }

    @Override
    public ResultDemo queryOrder(String username) {
        List<Userorder> list=querycommodity.queryOrder(username);
        if ( list !=null && list.size()>0){
            JSONArray jsonArray=JSONArray.fromObject(list);
            return ResultDemo.ok().meta("data",jsonArray);
        }
        return ResultDemo.error().status(201);
    }
}
