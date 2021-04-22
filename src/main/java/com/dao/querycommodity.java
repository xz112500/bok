package com.dao;

import com.pojo.VO.commodityVO;

import java.util.List;

public interface querycommodity {
    List<commodityVO> query(int num,int page);
    List<commodityVO> search(String name);
}
