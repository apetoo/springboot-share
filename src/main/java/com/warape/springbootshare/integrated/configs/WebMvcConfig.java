package com.warape.springbootshare.integrated.configs;

import com.warape.springbootshare.integrated.interceptors.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 万明宇
 * @date 2018/8/7 14:48
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 解决跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");

//        .allowedOrigins("http://192.168.1.97")
//        .allowedMethods("GET", "POST")
//        .allowCredentials(false).maxAge(3600);
    }

    @Bean
    public MyInterceptor logHandlerInterceptor() {
        return new MyInterceptor();
    }

    //增加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logHandlerInterceptor())    //指定拦截器类
                .addPathPatterns("/demo")
                .excludePathPatterns("/static/**").excludePathPatterns("/templates/**");
    }


}
