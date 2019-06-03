package com.warape.springbootshare.base.bootstrap;

import com.warape.springbootshare.base.condition.ConditionOnSystemProperty;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @program: springboot-share
 * @description:
 * @author: 万明宇
 * @create: 2019-05-28 16:54
 **/

public class SystemPropertyConditionBootstrap {


    //条件试注解  是否使bean生效
    @ConditionOnSystemProperty(name = "user.name", value = "warape")
    @Bean
    public String helloWorld() {
        return "条件试注解 hello world";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SystemPropertyConditionBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);


        String helloWorld = null;
        try {
            helloWorld = context.getBean("helloWorld", String.class);
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("没有找到该 (helloWorld) Bean");
        }

        System.out.println(helloWorld);

        context.close();
    }
}
