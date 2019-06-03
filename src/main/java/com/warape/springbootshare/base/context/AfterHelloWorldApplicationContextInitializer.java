package com.warape.springbootshare.base.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * @program: springboot-share
 * @description: after
 * @author: 万明宇 (warApe)
 * @create: 2019-05-30 18:32
 **/
public class AfterHelloWorldApplicationContextInitializer<C extends ConfigurableApplicationContext> implements Ordered, ApplicationContextInitializer<C> {
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("我是After  我后加载:" + applicationContext.getId());
    }
}
