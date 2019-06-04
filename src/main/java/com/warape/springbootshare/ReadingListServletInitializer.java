package com.warape.springbootshare;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @program: springboot-share
 * @description:
 * @author: 万明宇 (warApe)
 * @create: 2019-06-04 12:32
 **/
public class ReadingListServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootShareApplication.class);
    }
}
