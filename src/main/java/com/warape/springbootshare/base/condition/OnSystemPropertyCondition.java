package com.warape.springbootshare.base.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @program: springboot-share
 * @description:
 * @author: 万明宇
 * @create: 2019-05-28 16:51
 **/
public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionOnSystemProperty.class.getName());
        String name = (String) attributes.get("name");
        String value = (String) attributes.get("value");
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("properties");
        return property.equals(value);
    }

//    @Override
//    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        try {
//            context.getClassLoader().loadClass("org.springframework.jdbc.core.JdbcTemplate");
//            return true;
//        } catch (ClassNotFoundException e) {
//            return false;
//        }
//    }
}
