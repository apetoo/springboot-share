package com.warape.springbootshare.base.config.profile;

import com.warape.springbootshare.base.services.CalculateService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.Stream;


/**
 * @program: springboot-share
 * @description: 条件装配1.7
 * @author: 万明宇
 * @create: 2019-05-28 15:59
 **/
@Profile("java8")
@Configuration
public class Java8CalculateConfiguration implements CalculateService {

    public int sun(Integer ... values){
        System.out.println("我是java8");
        return Stream.of(values).reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        return "Java8CalculateConfiguration{}";
    }
}
