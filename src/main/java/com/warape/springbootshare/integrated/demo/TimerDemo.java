package com.warape.springbootshare.integrated.demo;

import com.warape.springbootshare.base.annotation.MyComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @program: springboot-share
 * @description: 定时器demo
 * @author: 万明宇 (warApe)
 * @create: 2019-05-31 10:58
 **/
@MyComponent
//@ConditionOnSystemProperty(name = "user.name", value = "warape")  //条件装配
public class TimerDemo {

    private static final Logger log = LoggerFactory.getLogger(TimerDemo.class);

    //可以组合使用

    //按照时间固定实行
    @Scheduled(cron = "0/1 * * * * ? ")
//    fixedRate(固定速率执) 是有一个时刻表的概念，在任务启动时，T1、T2、T3就已经排好了执行的时刻，比如1分、2分、3分，当T1的执行时间大于1分钟时，就会造成T2晚点，当T1执行完时T2立即执行
//    @Scheduled(fixedRate = 1)
//    fixedDelay 表示上个任务结束，到下个任务开始的时间间隔。无论任务执行花费多少时间，两个任务间的间隔始终是一致的。
//    @Scheduled(fixedDelay = 1)
//    初始延迟
//    @Scheduled(initialDelay = 1)
    public void timer01() {
        log.info("定时器 timer01 正在执行...");
    }

    @Scheduled(cron = "0/3 * * * * ? ")
    public void timer02() {
        log.info("定时器 timer02 正在执行...");
    }


}
