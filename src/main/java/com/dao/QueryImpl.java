package com.dao;

import com.entity.Admin;
import com.entity.Comment;
import com.entity.User;
import com.util.DBConn;
import com.util.Dateformat;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QueryImpl implements Query {
    Dateformat dateformat=new Dateformat();
    public static Date date=new Date();
    String Date= dateformat.dateformat(date);
    public Boolean query(String username, String password) {
        Object object = null;
        try {
            JdbcTemplate jdbcTemplate = null;
            try {
                jdbcTemplate = new JdbcTemplate(DBConn.getDataSource());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String SQL = "select distinct username,password from user where username=? and password=?";
            assert jdbcTemplate != null;
            object = jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    public Boolean queryAdmin(String username, String password) {
        Object object = null;
        try {
            JdbcTemplate jdbcTemplate = null;
            try {
                jdbcTemplate = new JdbcTemplate(DBConn.getDataSource());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String SQL = "select distinct username,password from admin where username=? and password=?";
            assert jdbcTemplate != null;
            object = jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<Admin>(Admin.class), username, password);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean insert(User user) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConn.getDataSource());
            String SQL = "insert into user (username, password,email) values (?,?,?)";
            int s = jdbcTemplate.update(SQL, user.getUsername(), user.getPassword(), user.getEmail());
            if (s > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean queryByName(String username) {
        Object o = null;

        JdbcTemplate jdbcTemplate = null;
        try {
            jdbcTemplate = new JdbcTemplate(DBConn.getDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String SQL = "select username from user where username=?";
        try {
            assert jdbcTemplate != null;
            o = jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    @Override
    public Comment comment(Comment comment) {
        JdbcTemplate jdbcTemplate=null;
        try {
       jdbcTemplate=new JdbcTemplate(DBConn.getDataSource());
       String SQL="insert into comment (username,date,comment) values (?,?,?)";
       int i=jdbcTemplate.update(SQL,comment.getUsername(),Date,comment.getComment());
       if (i>0){
           return new Comment(comment.getUsername(), Date, comment.getComment());
       }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
        
