package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class order {
    private Integer id;
    private String username;
    private String commodity;
    private Integer price;
    private String address;
    private String number;
    private String date;
}
