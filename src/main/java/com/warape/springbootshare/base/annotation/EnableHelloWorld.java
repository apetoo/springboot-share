package com.warape.springbootshare.base.annotation;

//import org.springframework.context.annotation.ConfigurationClass;
//import org.springframework.context.annotation.ConfigurationClassParser;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
import java.util.Collection;

/**
 * @program: springboot-share
 * @description: 模块装配
 * @author: 万明宇
 * @create: 2019-05-28 15:20
 *
 * 在以下类中被调用  使得没被扫描的类被自动装配
 * @see org.springframework.context.annotation.ConfigurationClassParser#processImports(ConfigurationClass, ConfigurationClassParser.SourceClass, Collection, boolean)
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(HelloWorldConfiguration.class)  不灵活
@Import(HelloWorldImportSelector.class)  //灵活
public @interface EnableHelloWorld {
}
