package org.example.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @Author 85217
 * @Modify 2021-11-16 23:49
 * @Description <p> </p>
 * <p> Talk is cheap . Show me the code! </p>
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor asyncThreadPoolExecutor() {

        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime=100L;
        TimeUnit unit= TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue=new LinkedBlockingQueue<>(100);
        ThreadFactory threadFactory= Executors.defaultThreadFactory();
        RejectedExecutionHandler handler=new ThreadPoolExecutor.AbortPolicy();
        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler);
    }


}
