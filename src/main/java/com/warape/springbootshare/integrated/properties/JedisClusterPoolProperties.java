package com.warape.springbootshare.integrated.properties;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 万明宇
 * @date 2018/5/14 14:25
 */
@Data
public class JedisClusterPoolProperties {

    private int maxTotal;
    private int maxIdle;
    private int maxWaitMillis;
}