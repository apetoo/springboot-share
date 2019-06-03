package com.warape.springbootshare.base.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @program: springboot-share
 * @description: after
 * @author: 万明宇 (warApe)
 * @create: 2019-05-30 18:32
 **/
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class BeforeHelloWorldApplicationContextInitializer<C extends ConfigurableApplicationContext> implements ApplicationContextInitializer<C> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("我是before 我先加载:" + applicationContext.getId());
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
    }
}
