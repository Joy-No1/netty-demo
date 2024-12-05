package com.xiaobin.netty.c2;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author Joy
 * @date: 2024/12/5 18:42
 * @description: 测试EventLoop
 * Good Luck!!!
 */

@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        //1. 创建时间循环组
        EventLoopGroup group = new NioEventLoopGroup(2);  //可以处理IO事件 普通任务 定时任务
//  EventLoopGroup group = new DefaultEventLoopGroup();  //可以处理IO事件 普通任务 定时任务
        //2. 获取下一个事件循环对象
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        //3. 执行普通任务
      /*  group.next().submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("ok");
        });*/


        //4. 执行定时任务
        group.next().scheduleAtFixedRate(() -> {
            log.debug("ok");
        }, 3L, 1L, TimeUnit.SECONDS);
        log.debug("main");
    }
}
