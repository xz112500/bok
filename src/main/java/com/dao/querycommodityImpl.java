package com.dao;

import com.pojo.Order;
import com.pojo.VO.commodityVO;
import com.util.DBConn;
import com.util.Dateformat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class querycommodityImpl implements querycommodity{
    @Override
    public List<commodityVO> query(int page,int num) {
        try {
          JdbcTemplate jdbcTemplate=new JdbcTemplate(DBConn.getDataSource());
            String SQL="select * from commodity limit ?,?";
            List<commodityVO> list=jdbcTemplate.query(SQL, new BeanPropertyRowMapper<commodityVO>(commodityVO.class),(num-1) * page,page);
           if (list.size()>0){
               return list;
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<commodityVO> search(String name) {
       try{
           JdbcTemplate jdbcTemplate=new JdbcTemplate(DBConn.getDataSource());
           String SQL="select * from commodity where name like concat('%',?,'%')";
          List<commodityVO> list=jdbcTemplate.query(SQL,new BeanPropertyRowMapper<>(commodityVO.class),name);
          if (list.size()>0){
              return list;
          }
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public List<Order> insert(Order order) {
        Date date=new Date();
        Dateformat dateformat=new Dateformat();
        List<Order> list=new ArrayList<>();
        try{
            JdbcTemplate jdbcTemplate=new JdbcTemplate(DBConn.getDataSource());
            String SQL="insert into Uorder (numbers,uname,username,commodity,price,address,counts,dates,phone,createtime) values (?,?,?,?,?,?,?,?,?,?)";
            int S=jdbcTemplate.update(SQL,order.getNumbers(),order.getUname(),order.getUsername(),order.getCommodity(),order.getPrice(),order.getAddress(),order.getCounts(),order.getDates(),order.getPhone(),dateformat.dateformat(date));
            list.add(new Order(order.getNumbers(),order.getUname(),order.getUsername(),order.getCommodity(),order.getPrice(),order.getAddress(),order.getCounts(),order.getDates(),order.getPhone(),dateformat.dateformat(date)));
           return S>0 ?  list : null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
