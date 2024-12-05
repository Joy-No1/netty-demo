package com.xiaobin.nio.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

import static com.xiaobin.nio.c1.bytebuffer.ByteBufferUtil.debugRead;

/**
 * @author Joy
 * @date: 2024/12/1 14:41
 * @description: 测试阻塞 使用NIO理解阻塞模式
 * Good Luck!!!
 */

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        //1. 创建Selector 管理多个channel
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        //2. 建立selector 和channel的联系 (注册)
        //selectionKey 事件发生后,通过它可以得到事件和channel事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        log.debug("register key:{}", sscKey);
        //只关注accept事件 0表示不关注任何事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8087));
        while (true) {
            //3. select方法 没有事件 线程就阻塞,有事件才会恢复运行
            selector.select();

            //4. 处理事件,selectedKeys 内部包含了所有发生的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //处理key  要从selectedKeys 集合中删除,否则下次处理会有问题
                iterator.remove();
                log.debug("key:{} ", key);
                //5. 区分事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", channel);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    try {
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        int read = channel.read(buffer);
                        if (read==-1){

                        }else {
                            buffer.flip();
                            debugRead(buffer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                    }
                }
//                key.cancel();
            }
        }
    }
}
