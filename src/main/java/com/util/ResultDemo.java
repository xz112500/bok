package com.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDemo {
    private Boolean success;
    private Integer status;
    private String message;
    private Map<String, Object> meta = new HashMap<String,Object>();
    private Map<String, Object> data = new HashMap<String,Object>();

    public static ResultDemo ok(){
        ResultDemo r=new ResultDemo();
        r.setSuccess(ResultCode.SUCCESS.getSuccess());
        r.setStatus(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        return r;
    }
    public static ResultDemo error(){
        ResultDemo r=new ResultDemo();
        r.setSuccess(ResultCode.ERROR.getSuccess());
        r.setStatus(ResultCode.ERROR.getCode());
        r.setMessage(ResultCode.ERROR.getMessage());
        return r;
    }
    public static ResultDemo UnKnow(){
        ResultDemo r=new ResultDemo();
        r.setSuccess(ResultCode.UNKNOWN_REASON.getSuccess());
        r.setStatus(ResultCode.UNKNOWN_REASON.getCode());
        r.setMessage(ResultCode.UNKNOWN_REASON.getMessage());
        return r;
    }

    public  ResultDemo success(Boolean success){
       this.setSuccess(success);
       return this;
    }
    public ResultDemo status(Integer status){
        this.setStatus(status);
        return this;
    }
    public ResultDemo message(String message){
        this.setMessage(message);
        return this;
    }
    public ResultDemo meta(String key,Object value){
        this.meta.put(key,value);
        return this;
    }
    public ResultDemo data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
    public ResultDemo data(Map<String,Object> map){
        this.setMeta(map);
        return this;
    }
    public ResultDemo meta(Map<String,Object> map){
        this.setMeta(map);
        return this;
    }
}
