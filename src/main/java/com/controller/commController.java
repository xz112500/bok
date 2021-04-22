package com.controller;


import com.service.commService;
import com.util.ResultDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class commController {
    @Autowired
    commService commService;
    @RequestMapping(value = "/commodity/list",method = RequestMethod.GET)
    public ResultDemo query(@RequestParam(value = "search",required = false) String search,@RequestParam(value = "page",defaultValue = "1") int page,@RequestParam(value = "num",defaultValue = "5") int num){
        return commService.query(num,page,search);
    }
}
