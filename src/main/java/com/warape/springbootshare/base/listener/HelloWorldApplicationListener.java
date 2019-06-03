package com.warape.springbootshare.base.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @program: springboot-share
 * @description: 监听事件 {@link ApplicationListener}  {@link ContextRefreshedEvent}
 * @author: 万明宇 (warApe)
 * @create: 2019-05-30 19:59
 **/
public class HelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("HelloWorld 我已经refresh了");
    }
}
