package com.service;

import com.dao.querycommodityImpl;
import com.util.ResultDemo;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class commServiceImpl implements commService{
    @Autowired
    querycommodityImpl querycommodity;
    @Override
    public ResultDemo query(int num,int page,String search) {
        JSONArray jsonArray=JSONArray.fromObject(querycommodity.query(num,page));
        JSONArray jsonArray1=JSONArray.fromObject(querycommodity.search(search));
       return search.isEmpty() ?  ResultDemo.ok().meta("data",jsonArray) : ResultDemo.ok().meta("data",jsonArray1);
    }
}
