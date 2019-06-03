package com.warape.springbootshare.base.bootstrap;

import com.warape.springbootshare.base.services.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: springboot-share
 * @description: 启动类条件注解
 * @author: 万明宇
 * @create: 2019-05-28 16:03
 **/
@ComponentScan(basePackages = "com.warape.springbootshare.base.config.profile")
public class CalculateBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("java8")
                .run(args);

        //思想:抽象出接口,定义规范  具体调用时子类实现(spring插拔式)
        CalculateService calculateService = context.getBean(CalculateService.class);
        int sun = calculateService.sun(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("sun = " + sun);
        context.close();
    }
}
