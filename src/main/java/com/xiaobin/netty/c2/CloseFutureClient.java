package com.xiaobin.netty.c2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author Joy
 * @date: 2024/12/6 20:27
 * @description: channelFuture
 * Good Luck!!!
 */

@Slf4j
public class CloseFutureClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                }).connect("localhost", 8080);
        Channel channel = channelFuture.sync().channel();
        log.debug("channel:{}", channel);
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if ("Q".equals(line) || "q".equals(line)) {
                    channel.close();  //异步操作  并非立刻关闭
//                    log.debug("处理关闭之后的操作...");
                    break;
                }
                channel.writeAndFlush(line);
            }
        }, "input").start();

        //获取CloseFuture对象  1.同步处理关闭 2 异步处理关闭
        ChannelFuture closeFuture = channel.closeFuture();
       /*  log.debug("waiting close...");
        closeFuture.sync();
        log.debug("处理关闭之后的操作...");*/

        closeFuture.addListener((ChannelFutureListener) future -> {
                    log.debug("处理关闭后的操作");
                    group.shutdownGracefully();  //优雅的停下来
                });
    }
}
