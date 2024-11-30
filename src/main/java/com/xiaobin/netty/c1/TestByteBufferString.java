package com.xiaobin.netty.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.xiaobin.netty.c1.ByteBufferUtil.debugAll;

/**
 * @author Joy
 * @date: 2024/11/30 15:15
 * @description: 测试字符串转ByteBuffer
 * Good Luck!!!
 */
public class TestByteBufferString {
    public static void main(String[] args) {
        //1. 字符串转为ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("hello".getBytes());
        debugAll(buffer);

        //2. CharSet
        ByteBuffer hello = StandardCharsets.UTF_8.encode("hello");
        debugAll(hello);

        //3. wrap
        ByteBuffer buffer1 = ByteBuffer.wrap("hello".getBytes());
        System.out.println(buffer1);

        buffer1.flip();
        String str1 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println("str1 = " + str1);
    }
}
