package com.warape.springbootshare.integrated.handler;

import com.warape.springbootshare.integrated.exceptions.AppException;
import com.warape.springbootshare.integrated.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 公共异常处理类
 * @author 万明宇
 * @version 1.0.0
 * @date Create in 2018/2/6 下午5:22
 */
@ControllerAdvice
@Slf4j
public class AppExceptionHandler {


    /**
     * 全局自定义异常
     * @author  万明宇
     * @param e 异常
     * @return  结果
     */
    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public ResponseResult<Object> appLoginException(AppException e){
        ResponseResult.ResponseResultBuilder<Object> builder = ResponseResult.builder();
        log.error("运行时自定义异常!",e);
        return builder.msg(e.getMsg()).code(e.getCode()).build();
    }

    /**
     * 全局异常处理调转页面
     * @author  万明宇
     * @param e 异常
     * @return  异常页面
     */
    @ExceptionHandler(value = Exception.class)
    public String handle(Exception e){
        log.error("运行时系统异常",e);
        return "error.ftl";
    }

}
