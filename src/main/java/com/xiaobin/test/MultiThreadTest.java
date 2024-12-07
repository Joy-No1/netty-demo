package com.xiaobin.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Joy
 * @date: 2024/12/7 14:44
 * @description: 测试多线程
 * Good Luck!!!
 */

@Slf4j
public class MultiThreadTest {

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(20),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) {

    }

    public String testCallable() {
        Callable<String> callable = () -> {
            return null;
        };
        return "";
    }
}
