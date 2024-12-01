package com.xiaobin.netty.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.xiaobin.netty.c1.bytebuffer.ByteBufferUtil.debugRead;

/**
 * @author Joy
 * @date: 2024/12/1 14:41
 * @description: 测试阻塞 使用NIO理解阻塞模式
 * Good Luck!!!
 */

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        //0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //1. 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.configureBlocking(false);//切换为非阻塞模式

        //2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8087));

        //3. accept 建立与客户端的连接 SocketChannel用来与客户端进行通信
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
//            log.debug("connecting.....");
            SocketChannel sc = ssc.accept(); //阻塞方法,线程停止运行  ssc.configureBlocking(false);//切换为非阻塞模式
            if (Objects.nonNull(sc)) {
                log.debug("connected.....{}", sc);
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {

                //4. 接收客户端发送的数据
//                log.debug("before.....{}", channels);

                int read = channel.read(buffer);//非阻塞 线程仍然会运行 如果没有读到数据 read返回0
                if (read > 0) {
                    buffer.flip();

                    debugRead(buffer);

                    buffer.clear();
                    log.debug("after.....{}", channel);
                }
            }

        }
    }
}
