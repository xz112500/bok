package com;

import com.dao.Query;
import com.dao.QueryImpl;
import com.entity.Comment;
import com.util.Dateformat;
import com.util.Gettoken;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class test {
    Dateformat dateformat=new Dateformat();
    Gettoken gettoken=new Gettoken();
    Query query=new QueryImpl();
    Date date=new Date();
    @Test
    public void test(){
        try {
            Comment comment=new Comment("user", dateformat.dateformat(date),"666");
            System.out.println(comment);
            /*System.out.println(query.queryAdmin("admin","123456"));
            System.out.println(query.query("user","123456"));*/
            System.out.println(gettoken.gettoken("user","123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
