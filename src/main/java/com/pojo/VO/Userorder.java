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
    private String uname;
    private String commodity;
    private Integer price;
    private String address;
    private Integer counts;
    private String dates;
    private String phone;
    private String createtime;

    public Userorder(String username, String commodity, Integer price, String address, Integer counts, String createtime, String phone) {
        this.username = username;
        this.commodity = commodity;
        this.price = price;
        this.address = address;
        this.counts = counts;
        this.createtime = createtime;
        this.phone = phone;
    }
}
