package com.warape.springbootshare.base.annotation;

import com.warape.springbootshare.base.config.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: springboot-share
 * @description:
 * @author: 万明宇
 * @create: 2019-05-28 15:23
 **/
public class HelloWorldImportSelector implements ImportSelector {

    /**
     * 返回类全路径用来初始化bean
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String name = HelloWorldConfiguration.class.getName();
        System.out.println(name);
        return new String[]{name};
    }
}
