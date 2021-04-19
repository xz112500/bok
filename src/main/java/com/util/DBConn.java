package com.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DBConn{
    public static DataSource getDataSource() throws Exception{
        Properties pro=new Properties();
        InputStream in=DBConn.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            pro.load(in);
        }catch (Exception e){
            e.printStackTrace();
        }
        return DruidDataSourceFactory.createDataSource(pro);
    }


}
