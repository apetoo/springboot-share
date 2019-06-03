package com.warape.springbootshare;

import com.warape.springbootshare.integrated.demo.ExecutorDemo;
import com.warape.springbootshare.integrated.utils.StringTemplateRedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootShareApplicationTests {

    @Autowired
    private ExecutorDemo executorDemo;
    @Resource(name = "defaultStringTemplateRedisUtil")
    private StringTemplateRedisUtil stringTemplateRedisUtil;
    @Test
    public void contextLoads() {
    }


    @Test
    public void test01(){
        stringTemplateRedisUtil.set("war_ape","测试");
        String warApe = stringTemplateRedisUtil.get("war_ape");
        System.out.println("war_ape = " + warApe);
    }

    @Test
    public void test02(){
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                executorDemo.demo01();
                executorDemo.demo02();
            });
            thread.start();
        }

    }
}
