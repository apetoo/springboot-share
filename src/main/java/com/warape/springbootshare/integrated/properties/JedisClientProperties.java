package com.warape.springbootshare.integrated.properties;

import lombok.Data;

/**
 * @author 战猿点
 * @date 2018/5/14 14:23
 */
@Data
public class JedisClientProperties {

    private int port;
    private String hostName;
    private int connectTimeout;
    private int readTimeout;

}