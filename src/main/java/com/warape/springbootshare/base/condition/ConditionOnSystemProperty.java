package com.warape.springbootshare.base.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @program: springboot-share
 * @description:
 * @author: 万明宇
 * @create: 2019-05-28 16:48
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionOnSystemProperty {

    String name();

    String value();
}
