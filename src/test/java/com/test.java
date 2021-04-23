package com;

import com.dao.Query;
import com.dao.QueryImpl;
import com.dao.querycommodityImpl;
import com.pojo.Order;
import com.pojo.VO.Comment;
import com.pojo.dbUser;
import com.util.Dateformat;
import com.util.Gettoken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class test {
    Dateformat dateformat=new Dateformat();
    Gettoken gettoken=new Gettoken();
    Query query=new QueryImpl();
    Date date=new Date();
    com.dao.querycommodity querycommodity=new querycommodityImpl();
    @Test
    public void test(){
        try {
/*            Comment comment=new Comment("user", dateformat.dateformat(date),"666");
            System.out.println(comment);
            System.out.println(query.queryByName("xw"));
            System.out.println(gettoken.gettoken("user","123456"));
            System.out.println(querycommodity.query());
            String a="123456";
            dbUser dbUser=new dbUser("user","123456","23@qq.com");
            String encoding= DigestUtils.md5DigestAsHex(a.getBytes(StandardCharsets.UTF_8));
            System.out.println(encoding);*/
    /*        String a="1";
            String encodingPass = DigestUtils.md5DigestAsHex(a.getBytes());
            System.out.println(encodingPass);*/
        /*    System.out.println(query.insert(new dbUser("xa","123456","22@qq.com")));
            System.out.println(query.queryByName("xa").get(0).getPassword());*/
          /*  System.out.println(querycommodity.search("小"));*/
   /*         System.out.println(dateformat.dateformat(date));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
