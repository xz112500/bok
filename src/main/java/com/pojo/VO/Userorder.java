package com.pojo.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userorder implements Serializable {
    private String username;

    public Userorder(String uname,String username, String commodity, int price, String address, int counts, String dates, String phone) {
        this.uname=uname;
        this.username = username;
        this.commodity = commodity;
        this.price = price;
        this.address = address;
        this.counts = counts;
        this.dates = dates;
        this.phone = phone;
    }
    private String uname;
    private String commodity;
    private int price;
    private String address;
    private int counts;
    private String dates;
    private String phone;
    private String createtime;
}
