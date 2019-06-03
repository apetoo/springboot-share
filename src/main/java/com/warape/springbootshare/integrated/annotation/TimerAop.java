package com.warape.springbootshare.integrated.annotation;

import java.lang.annotation.*;

/**
 * @program: springboot-share
 * @description: 时间打印切点
 * @author: 万明宇 (warApe)
 * @create: 2019-05-31 15:58
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface TimerAop {
}
