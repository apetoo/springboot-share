package com.warape.springbootshare.base.config;

import com.warape.springbootshare.base.demo.scan.DemoScan;
import org.springframework.context.annotation.Bean;

/**
 * @program: springboot-share
 * @description:
 * @author: 万明宇
 * @create: 2019-05-28 15:19
 **/

//@Configuration  //因在HelloWorldImportSelector中使用了所以该bean已经注册过了
public class HelloWorldConfiguration {

    public HelloWorldConfiguration() {
        System.out.println("我被初始化了...");
    }

    @Bean
    public DemoScan demoScan(){
        DemoScan demoScan = new DemoScan();
        demoScan.setName("warApe");
        demoScan.setPassword("wanmingyu");
        return demoScan;
    }
}
