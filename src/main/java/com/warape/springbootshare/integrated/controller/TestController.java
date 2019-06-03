package com.warape.springbootshare.integrated.controller;

import com.warape.springbootshare.integrated.annotation.TimerAop;
import com.warape.springbootshare.integrated.exceptions.AppException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot-share
 * @description: controller
 * @author: 万明宇 (warApe)
 * @create: 2019-05-31 14:34
 **/
@RestController
public class TestController {

    @GetMapping("/demo")
    public String demo(){
        return "测试拦截器";

    }
    @GetMapping("/aop")
    @TimerAop
    public String aop(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "测试AOP";
    }

    @GetMapping("/myError01")
    public String myError01(){
        if(1==1){
            throw new AppException("4009","1=1异常了");
        }

        return "myError01";
    }

    @GetMapping("/myError02")
    public String myError02(){
        TestController testController = (TestController) new Object();

        return "myError02";
    }

}
