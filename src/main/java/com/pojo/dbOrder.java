package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class dbOrder implements Serializable {
    private Integer numbers;
    private String uname;
    private String username;
    private String commodity;
    private Integer price;
    private String address;
    private Integer counts;
    private Integer allcount;
    private String dates;
    private String phone;
    private String createtime;


    public dbOrder(Integer numbers, String uname, String username, String commodity, Integer price, String address, Integer counts, String dates, String phone, String createtime) {
        this.uname=uname;
        this.numbers = numbers;
        this.username = username;
        this.commodity = commodity;
        this.price = price;
        this.address = address;
        this.counts = counts;
        this.dates = dates;
        this.phone = phone;
        this.createtime=createtime;
    }


}
