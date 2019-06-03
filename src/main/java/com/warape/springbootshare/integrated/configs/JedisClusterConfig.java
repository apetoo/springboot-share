package com.warape.springbootshare.integrated.configs;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.warape.springbootshare.SpringbootShareApplication;
import com.warape.springbootshare.integrated.properties.JedisClusterConfigProperties;
import com.warape.springbootshare.integrated.properties.JedisClusterPoolProperties;
import com.warape.springbootshare.integrated.utils.RedisUtil;
import com.warape.springbootshare.integrated.utils.StringTemplateRedisUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * 集群版
 *
 * @author 万明宇(战猿点)
 * @date 2018/7/29 18:03
 * @see SpringbootShareApplication @EnableRedisConfigType
 */
//@Configuration  //查看入口类模式注解  @EnableRedisConfigType
public class JedisClusterConfig {

    private JedisClusterPoolProperties jedisClusterPoolProperties;
    private JedisClusterConfigProperties JedisClusterConfigProperties;

    @Autowired
    public JedisClusterConfig(JedisClusterPoolProperties jedisClusterPoolProperties, JedisClusterConfigProperties jedisClusterConfigProperties) {
        this.jedisClusterPoolProperties = jedisClusterPoolProperties;
        JedisClusterConfigProperties = jedisClusterConfigProperties;
    }

    @Bean
    public RedisConnectionFactory jedisConnectionFactoryCluster1() {

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(JedisClusterConfigProperties.getJedisClusterNodes());
        redisClusterConfiguration.setMaxRedirects(JedisClusterConfigProperties.getMaxRedirections());
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        //最大空闲
        genericObjectPoolConfig.setMaxIdle(jedisClusterPoolProperties.getMaxIdle());
        //链接池中最大连接数
        genericObjectPoolConfig.setMaxTotal(jedisClusterPoolProperties.getMaxTotal());
        //最小空闲
        genericObjectPoolConfig.setMinIdle(jedisClusterPoolProperties.getMaxIdle());
        //连接池资源耗尽后  最大等待时间
        genericObjectPoolConfig.setMaxWaitMillis(jedisClusterPoolProperties.getMaxWaitMillis());

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofMillis(JedisClusterConfigProperties.getConnectTimeout()))
                .readTimeout(Duration.ofMillis(JedisClusterConfigProperties.getReadTimeout()))
                .usePooling().poolConfig(genericObjectPoolConfig)
                .build();
        return new JedisConnectionFactory(redisClusterConfiguration, jedisClientConfiguration);
    }

    /**
     * redisClusterTemplateCluster1
     *
     * @param jedisConnectionFactoryCluster1 参数名为beanName
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisClusterTemplateCluster1(RedisConnectionFactory jedisConnectionFactoryCluster1) {
        //创建一个模板类
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        //将刚才的redis连接工厂设置到模板类中
        template.setConnectionFactory(jedisConnectionFactoryCluster1);
        //初始化redis key中的序列化方式 StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        //初始化redis value中的序列化方式 fastjson序列化
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        template.setHashValueSerializer(serializer);
        template.setValueSerializer(serializer);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplateCluster1(RedisConnectionFactory jedisConnectionFactoryCluster1) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactoryCluster1);
        return stringRedisTemplate;
    }

    /**
     * 序列化不同(对象)
     *
     * @param redisClusterTemplateCluster1
     * @return
     */
    @Bean
    public RedisUtil redisUtilCluster1(RedisTemplate<String, Object> redisClusterTemplateCluster1) {
        return new RedisUtil(redisClusterTemplateCluster1);
    }


    /**
     * (可视的数据)
     *
     * @param stringRedisTemplateCluster1
     * @return
     */
    @Bean
    public StringTemplateRedisUtil StringRedisUtilCluster1(StringRedisTemplate stringRedisTemplateCluster1) {
        return new StringTemplateRedisUtil(stringRedisTemplateCluster1);
    }


}
