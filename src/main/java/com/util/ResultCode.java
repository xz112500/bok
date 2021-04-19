package com.util;


public enum ResultCode {
    SUCCESS(true,200,"成功!"),
    ERROR(false,404,"失败!"),
    QUERYERROR(false,20081,"查询失败"),
    LOGIN_AUTH(false, 28004, "需要登录"),
    LOGIN_ACL(false, 28005, "没有权限"),
    LOGIN_ERROR(false, 23005, "登录失败"),
    UNKNOWN_REASON(false, 20001, "未知错误"),
;
    public Boolean getSuccess() {
        return success;
    }
    public Integer getCode() {
        return Code;
    }
    public String getMessage() {
        return message;
    }

    private Boolean success;
    private Integer Code;
    private String message;
    ResultCode(Boolean success, Integer code, String message) {
        this.success = success;
        Code = code;
        this.message = message;
    }
    @Override
    public String toString() {
        return "ResultCode{" +
                "success=" + success +
                ", Code=" + Code +
                ", message='" + message + '\'' +
                '}';
    }
}
