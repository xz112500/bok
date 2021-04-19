package com;

import com.dao.Query;
import com.dao.QueryImpl;
import org.junit.jupiter.api.Test;

public class test {
    Query query=new QueryImpl();
    @Test
    public void test(){
        try {
            System.out.println(query.queryAdmin("admin","123456"));
            System.out.println(query.query("user","123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
