package com.xiaobin.netty.c1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Joy
 * @date: 2024/12/4 20:49
 * @description: netty客户端测试
 * Good Luck!!!
 */

@Slf4j
public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        //1. 启动类
        new Bootstrap()
                //2. 添加EventLoop
                .group(new NioEventLoopGroup())
                //3. 选择客户端channel 实现
                .channel(NioSocketChannel.class)
                //4. 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override  //在连接建立后被调用
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                //5. 连接到服务器
                .connect("localhost", 8086)
                .sync()
                .channel()
                //6. 向服务器发送数据
                .writeAndFlush("hello netty");
    }
}
