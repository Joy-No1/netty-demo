package com.xiaobin.netty.c2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Joy
 * @date: 2024/12/7 14:53
 * @description: 测试JDK中的Future
 * Good Luck!!!
 */

@Slf4j
public class TestJdkFuture {

    //1. 线程池
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(20),
            new ThreadPoolExecutor.AbortPolicy()
    );


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //2. 提交任务
        Future<Integer> future = executor.submit(() -> {
            log.debug("执行计算");
            TimeUnit.SECONDS.sleep(3);
            return 50;
        });

        //主线程可以通过future获取结果
        log.debug("主线程等待结果");
        Integer result = future.get();
        log.debug("结果:{}", result);
    }
}
