package com.warape.springbootshare.integrated.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @program: springboot-share
 * @description: 自定义模式配置
 * @author: 万明宇 (warApe)
 * @create: 2019-05-31 13:54
 **/
public class RedisConfigTypeSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableRedisConfigType.class.getName());

        Class[] array = (Class[]) attributes.get("configClass");
        String[] strings = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            String name = array[i].getName();
            strings[i] = name;
        }

        return strings;
    }
}
