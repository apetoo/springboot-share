package com.warape.springbootshare.integrated.exceptions;


/**
 * @author tianhaoliang@sinovatech.com
 * @version 1.0.0
 * @date Create in 2018/2/6 下午3:09
 */

public class AppException extends RuntimeException{

    private  String code;

    private String msg;

    public AppException(String code, String message){
        super(message);
        this.code=code;
        this.msg=message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
