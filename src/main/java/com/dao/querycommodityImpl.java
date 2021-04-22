package com.dao;

import com.pojo.VO.commodityVO;
import com.util.DBConn;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
}
