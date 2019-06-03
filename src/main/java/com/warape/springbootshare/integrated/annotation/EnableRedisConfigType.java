package com.warape.springbootshare.integrated.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @program: springboot-share
 * @description: 手动配置redis配置类型
 * @author: 万明宇 (warApe)
 * @create: 2019-05-31 12:58
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RedisConfigTypeSelector.class) //条件装配
public @interface EnableRedisConfigType {

    /**
     * @return
     * 配置名
     */
    Class<?>[] configClass() default {};


}
