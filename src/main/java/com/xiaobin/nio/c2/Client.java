package com.xiaobin.nio.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author Joy
 * @date: 2024/12/1 14:52
 * @description: 客户端
 * Good Luck!!!
 */

@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8087));
        System.out.println("waiting...");
    }
}
