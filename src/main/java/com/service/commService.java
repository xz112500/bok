package com.service;

import com.pojo.VO.commodityVO;
import com.util.ResultDemo;

import java.util.List;

public interface commService {
    ResultDemo query(int num,int page,String search);
}
