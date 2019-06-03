package com.warape.springbootshare.integrated.properties;

import lombok.Data;

import java.util.List;

/**
 * Jedis集群配置类
 *
 * @author 战猿点
 */
@Data
public class JedisClusterConfigProperties {
    private int connectTimeout;
    private int readTimeout;
    private int maxRedirections;
    private List<String> jedisClusterNodes;
}
