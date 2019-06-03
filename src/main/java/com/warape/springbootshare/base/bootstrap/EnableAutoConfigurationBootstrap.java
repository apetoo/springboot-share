package com.warape.springbootshare.base.bootstrap;

import com.warape.springbootshare.base.annotation.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * EnableAutoConfiguration 引导类
 * @program: springboot-share
 * @description:
 * @author: 万明宇
 * @create: 2019-05-28 19:03
 *
 * 条件判断  user.name=warape
 * 模块注解  @Configuration
 * @Enable @EnableHelloWorld -->  HelloWorldImportSelector --> HelloWorldConfiguration --> demoScan
 **/
//@EnableCaching
//@EnableScheduling
    @EnableHelloWorld
@ComponentScan(basePackages = "com.warape.springbootshare.base.other") //因在HelloWorldImportSelector返回的类路径已经初始化bean了所以并不需要扫描
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)  //去除数据库的自动装配
public class EnableAutoConfigurationBootstrap {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAutoConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);


        context.close();
    }
}
