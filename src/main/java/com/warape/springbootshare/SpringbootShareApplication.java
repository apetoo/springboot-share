package com.warape.springbootshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


//线程池
@EnableAsync
//定时器
@EnableScheduling
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
//模式注解  不同环境使用不同配置(这里指定redis加载的是哪个类)
public class SpringbootShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShareApplication.class, args);
    }
}
