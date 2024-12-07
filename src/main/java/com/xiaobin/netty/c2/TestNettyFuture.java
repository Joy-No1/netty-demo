package com.xiaobin.netty.c2;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Joy
 * @date: 2024/12/7 15:01
 * @description: 测试Netty中的Future
 * Good Luck!!!
 */

@Slf4j
public class TestNettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.debug("主线程:{}", Thread.currentThread().getName());
        EventLoopGroup group = new NioEventLoopGroup();
        Future<Integer> future = group.submit(() -> {
            log.debug("执行计算");
            TimeUnit.SECONDS.sleep(5);
            return 70;
        });
        //同步方式
       /* log.debug("主线程等待结果");
        Integer result = future.get();
        log.debug("结果:{}", result);*/

        //异步方式
        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.debug("接收结果:{}", future.getNow());
            }
        });
    }
}
