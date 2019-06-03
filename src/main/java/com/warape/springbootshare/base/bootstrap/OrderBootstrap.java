package com.warape.springbootshare.base.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: springboot-share
 * @description: 加载顺序启动类
 * @author: 万明宇 (warApe)
 * @create: 2019-05-30 18:30
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableHelloWorld
public class OrderBootstrap {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication();
        //指定入口类
        Set<String> sources = new HashSet<>();
        sources.add(OrderBootstrap.class.getName());
        application.setSources(sources);
        application.run(args);
    }
}
