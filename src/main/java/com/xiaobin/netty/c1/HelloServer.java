package com.xiaobin.netty.c1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author Joy
 * @date: 2024/12/4 20:34
 * @description: netty服务端
 * Good Luck!!!
 */


public class HelloServer {
    public static void main(String[] args) {
        //1.启动器 负责组装netty组件 启动服务器
        new ServerBootstrap()
                //2. BossEventLoopGroup 组  负责处理不同事件 包含线程和选择器
                .group(new NioEventLoopGroup())
                //3. 选择服务器的 ServerSocketChannel 实现
                .channel(NioServerSocketChannel.class)
                //4. Boss 负责处理连接  worker负责处理读写  能执行哪些操作 (handler)
                .childHandler(
                        //5. 和客户端进行数据读写的通道 Initializer初始化 负责添加别的Handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                //6. 添加具体的handler
                                ch.pipeline().addLast(new StringDecoder());  //将 ByteBuf 转为字符串
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { // 自定义handler
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                // 监听端口
                .bind(8086);
    }

}
