package com.warape.springbootshare.base.config.profile;

import com.warape.springbootshare.base.services.CalculateService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @program: springboot-share
 * @description: 条件装配1.7
 * @author: 万明宇
 * @create: 2019-05-28 15:59
 **/
@Profile("java7")
@Configuration
public class Java7CalculateConfiguration implements CalculateService {

    public int sun(Integer... values){

        System.out.println("我是java7");
        int result = 0;
        for (int value : values) {
            result = result+value;
        }

        return result;
    }

    @Override
    public String toString() {
        return "Java7CalculateConfiguration{}";
    }

}
