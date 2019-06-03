package com.warape.springbootshare.integrated.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-share
 * @description: 线城池demo
 * @author: 万明宇 (warApe)
 * @create: 2019-05-31 14:33
 **/
@Component
public class ExecutorDemo {

    private static final Logger log = LoggerFactory.getLogger(ExecutorDemo.class);

    @Async(value = "executorServiceTest01")
    public void demo01() {
//        log.info("当前线程:{}",Thread.currentThread().getName());
    }

    @Async(value = "executorServiceTest02")
    public void demo02() {

//        log.info("当前线程:{}",Thread.currentThread().getName());
    }

}
