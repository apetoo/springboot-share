package com.warape.springbootshare.base.config;

import com.warape.springbootshare.base.annotation.EnableHelloWorld;
import com.warape.springbootshare.base.condition.ConditionOnSystemProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot-share
 * @description: 自动装配
 * @author: 万明宇
 * @create: 2019-05-28 19:04
 **/
@Configuration  //spring模式注解
@EnableHelloWorld  //spring 模块装配
@ConditionOnSystemProperty(name = "user.name", value = "warape")  //条件装配
public class HelloWorldAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldAutoConfiguration.class);

    public HelloWorldAutoConfiguration() {
        log.info("自动装配:HelloWorldAutoConfiguration");
    }


}
