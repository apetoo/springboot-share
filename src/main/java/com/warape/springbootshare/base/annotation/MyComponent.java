package com.warape.springbootshare.base.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @program: springboot-share
 * @description: 自定义注解
 * @author: 万明宇
 * @create: 2019-05-28 14:58
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {

    String value() default "";
}
