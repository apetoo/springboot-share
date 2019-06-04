package com.warape.springbootshare.base.bootstrap;

import com.warape.springbootshare.base.demo.scan.DemoScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: springboot-share
 * @description: 自定义注解
 * @author: 万明宇
 * @create: 2019-05-28 14:55
 **/
@ComponentScan(basePackages = "com.warape.springbootshare.base.demo.scan")
//@EnableCaching
//@EnableHelloWorld
public class BootstrapOne {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(BootstrapOne.class)
                .web(WebApplicationType.NONE)
                .sources()
                .run(args);
        DemoScan demoScan = context.getBean(DemoScan.class);
        System.out.println(demoScan);
        context.close();

    }
}
