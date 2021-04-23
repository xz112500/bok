package com.pojo.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class commodityVO implements Serializable {
    private Integer id;
    private Integer numbers;
    private String name;
    private Integer price;
    private Integer count;
    private String imglink;
}
