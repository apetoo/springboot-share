package com.warape.springbootshare.integrated.configs;

import com.warape.springbootshare.integrated.executor.VisiableThreadPoolTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
//@EnableAsync
public class ExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Autowired
    private Environment environment;


    /**
     * TEST01
     * @return
     */
    @Bean
    public Executor executorServiceTest01() {
        logger.info("start asyncServiceExecutor-01");
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        Integer prizeCorePoolSize  = environment.getProperty("test01_core_pool_size", Integer.class);
        Integer prizeMaxPoolSize  = environment.getProperty("test01_max_pool_size", Integer.class);
        Integer prizeQueueCapacity  = environment.getProperty("test01_queue_capacity", Integer.class);
        String prize_name = environment.getProperty("test01_name");
        //配置核心线程数
        executor.setCorePoolSize(prizeCorePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(prizeMaxPoolSize);
        //配置队列大小
        executor.setQueueCapacity(prizeQueueCapacity);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(prize_name);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * TEST02
     * @return
     */
    @Bean
    public Executor executorServiceTest02() {
        logger.info("start asyncServiceExecutorOrder-02");
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        Integer prizeCorePoolSize = environment.getProperty("test02_core_pool_size", Integer.class);
        Integer prizeMaxPoolSize = environment.getProperty("test02_max_pool_size", Integer.class);
        Integer prizeQueueCapacity = environment.getProperty("test02_queue_capacity", Integer.class);
        String prizeName = environment.getProperty("test02_name");
        //配置核心线程数
        executor.setCorePoolSize(prizeCorePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(prizeMaxPoolSize);
        //配置队列大小
        executor.setQueueCapacity(prizeQueueCapacity);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(prizeName);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}