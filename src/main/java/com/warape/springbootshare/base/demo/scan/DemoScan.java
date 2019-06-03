package com.warape.springbootshare.base.demo.scan;

import com.warape.springbootshare.base.annotation.MyComponent;
import lombok.Data;

/**
 * @program: springboot-share
 * @description: 扫描
 * @author: 万明宇
 * @create: 2019-05-28 14:57
 **/
@MyComponent(value = "demo")
@Data
public class DemoScan {

    private String name;
    private String password;
}
