package com.warape.springbootshare.integrated.configs;

import com.warape.springbootshare.integrated.properties.JedisClientPoolProperties;
import com.warape.springbootshare.integrated.properties.JedisClientProperties;
import com.warape.springbootshare.integrated.properties.JedisClusterConfigProperties;
import com.warape.springbootshare.integrated.properties.JedisClusterPoolProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot-share
 * @description: bean加载config
 * @author: 万明宇 (warApe)
 * @create: 2019-05-31 14:58
 **/
@Configuration
public class BeanConfig {

    @Bean
    @ConfigurationProperties(prefix = "com.warape.redis.standalone.pool")
    public JedisClientPoolProperties jedisClientPoolProperties() {
        return new JedisClientPoolProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "com.warape.redis.standalone.client")
    public JedisClientProperties jedisClientProperties() {
        return new JedisClientProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "com.warape.redis.pool")
    public JedisClusterPoolProperties jedisClusterPoolProperties() {
        return new JedisClusterPoolProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "com.warape.redis.cluster")
    public JedisClusterConfigProperties jedisClusterConfigProperties() {
        return new JedisClusterConfigProperties();
    }


}
