package com.dao;

import com.pojo.VO.Comment;
import com.pojo.dbUser;
import com.util.DBConn;
import com.util.Dateformat;
import com.util.Gettoken;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class QueryImpl implements Query {
    Dateformat dateformat=new Dateformat();
    public static Date date=new Date();
    String Date= dateformat.dateformat(date);
    public List<dbUser> query(String username, String password) {
        List<dbUser> list;
        try {
            JdbcTemplate jdbcTemplate = null;
            try {
                jdbcTemplate = new JdbcTemplate(DBConn.getDataSource());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String SQL = "select distinct username,password from user where username=? and password=?";
            assert jdbcTemplate != null;
           list=jdbcTemplate.query(SQL, new BeanPropertyRowMapper<dbUser>(dbUser.class), username, password);
           return list;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public dbUser insert(dbUser user) {
        Gettoken gettoken=new Gettoken();
        try {
            dbUser DbUser=new dbUser();
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConn.getDataSource());

            String SQL = "insert into user (username, password,email,token,ip) values (?,?,?,?,?)";

            String encodePass=user.getPassword()+user.getUsername()+"fine";

            String encodingPass1 = DigestUtils.md5DigestAsHex(encodePass.getBytes());//对密码加密

            int s = jdbcTemplate.update(SQL, user.getUsername(),encodingPass1,user.getEmail(), gettoken.gettoken(user.getUsername(), user.getPassword()), user.getIp());
            if (s > 0) {
                DbUser.setUsername(user.getUsername());
                DbUser.setPassword(encodingPass1);
                DbUser.setEmail(user.getEmail());
                DbUser.setToken(gettoken.gettoken(user.getUsername(), user.getPassword()));
                return DbUser;//返回加密后的对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<dbUser> queryByName(String username) {
        JdbcTemplate jdbcTemplate = null;
        try {
            jdbcTemplate = new JdbcTemplate(DBConn.getDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String SQL = "select * from user where username=?";
        try {
            assert jdbcTemplate != null;
           return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<dbUser>(dbUser.class), username);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
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
        
