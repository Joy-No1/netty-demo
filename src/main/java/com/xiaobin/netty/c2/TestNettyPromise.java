package com.xiaobin.netty.c2;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author Joy
 * @date: 2024/12/7 15:12
 * @description: Netty中的promise
 * Good Luck!!!
 */

@Slf4j
public class TestNettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1. 准备EventLoop对象
        EventLoop eventLoop = new NioEventLoopGroup().next();

        //2. 主动创建Promise  实际就是结果容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        //3. 任意线程 执行计算后向promise填充结果
        new Thread(() -> {
            log.debug("开始计算");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            promise.setSuccess(80);
        }).start();

        //4. 设置接收结果的线程
        log.debug("等待结果...");
        log.debug("结果是:{}", promise.get());

    }
}
