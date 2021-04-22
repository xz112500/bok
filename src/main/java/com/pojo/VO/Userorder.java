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
    private String commodity;
    private Integer price;
    private String address;
    private String number;
    private String date;
}
