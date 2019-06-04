package com.warape.springbootshare.integrated.configs;


import com.warape.springbootshare.SpringbootShareApplication;
import com.warape.springbootshare.integrated.properties.JedisClientPoolProperties;
import com.warape.springbootshare.integrated.properties.JedisClientProperties;
import com.warape.springbootshare.integrated.utils.RedisUtil;
import com.warape.springbootshare.integrated.utils.StringTemplateRedisUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * redis单机版配置
 *
 * @author warApe
 * @date 2018/5/11 15:50
 * @see SpringbootShareApplication
 */
@Profile("local")
@Configuration
public class JedisClientConfig extends RedisAutoConfiguration {

    private JedisClientPoolProperties jedisClientPoolProperties;
    private JedisClientProperties jedisClientProperties;

    @Autowired
    public JedisClientConfig(JedisClientPoolProperties jedisClientPoolProperties, JedisClientProperties jedisClientProperties) {
        this.jedisClientPoolProperties = jedisClientPoolProperties;
        this.jedisClientProperties = jedisClientProperties;
    }

    /**
     * 单节点
     *
     * @return
     */
    @Bean
    public RedisConnectionFactory defaultJedisConnectionFactory() {
        //如果什么参数都不设置，默认连接本地6379端口
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setHostName(jedisClientProperties.getHostName());
        standaloneConfiguration.setPort(jedisClientProperties.getPort());


        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        //最大空闲
        genericObjectPoolConfig.setMaxIdle(jedisClientPoolProperties.getMaxIdle());
        //链接池中最大连接数
        genericObjectPoolConfig.setMaxTotal(jedisClientPoolProperties.getMaxTotal());
        //最小空闲
        genericObjectPoolConfig.setMinIdle(jedisClientPoolProperties.getMaxIdle());
        //连接池资源耗尽后  最大等待时间
        genericObjectPoolConfig.setMaxWaitMillis(jedisClientPoolProperties.getMaxWaitMillis());

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofMillis(jedisClientProperties.getConnectTimeout()))
                .readTimeout(Duration.ofMillis(jedisClientProperties.getReadTimeout()))
                .usePooling().poolConfig(genericObjectPoolConfig)
                .build();


        return new JedisConnectionFactory(standaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> defaultRedisTemplate(RedisConnectionFactory defaultJedisConnectionFactory) {
        //创建一个模板类
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        //将刚才的redis连接工厂设置到模板类中
        template.setConnectionFactory(defaultJedisConnectionFactory);
        StringRedisSerializer hashKeySerializer = new StringRedisSerializer();
        template.setKeySerializer(hashKeySerializer);
        template.setHashKeySerializer(hashKeySerializer);
        return template;
    }

    @Bean
    public StringRedisTemplate defaultStringRedisTemplate(RedisConnectionFactory defaultJedisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(defaultJedisConnectionFactory);
        return stringRedisTemplate;
    }

    /**
     * 序列化不同(对象)
     *
     * @param defaultRedisTemplate
     * @return
     */
    @Bean
    public RedisUtil defaultRedisUtil(RedisTemplate<String, Object> defaultRedisTemplate) {
        return new RedisUtil(defaultRedisTemplate);
    }

    /**
     * (可视的数据)
     *
     * @param defaultStringRedisTemplate
     * @return
     */
    @Bean
    public StringTemplateRedisUtil defaultStringTemplateRedisUtil(StringRedisTemplate defaultStringRedisTemplate) {
        return new StringTemplateRedisUtil(defaultStringRedisTemplate);
    }

}
