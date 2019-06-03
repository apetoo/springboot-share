package com.warape.springbootshare.integrated.utils;


/**
 * @program: thpeople_manager
 * @description: 统一响应
 * @author: 万明宇
 * @create: 2019-05-22 09:07
 **/
public class ResponseResult<T> {

    private String msg;
    private String code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    ResponseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseResult.ResponseResultBuilder<T> builder() {
        return new ResponseResult.ResponseResultBuilder();
    }

    public static class  ResponseResultBuilder<T>{

        private String msg;
        private String code;
        private T data;

        ResponseResultBuilder() {
        }

        public ResponseResult.ResponseResultBuilder<T> code( String code) {
            this.code = code;
            return this;
        }

        public ResponseResult.ResponseResultBuilder<T> msg( String msg) {
            this.msg = msg;
            return this;
        }

        public ResponseResult.ResponseResultBuilder<T> data( T data) {
            this.data = data;
            return this;
        }


        public ResponseResult<T> build() {
            return new ResponseResult(this.code, this.msg, this.data);
        }
    }
}
