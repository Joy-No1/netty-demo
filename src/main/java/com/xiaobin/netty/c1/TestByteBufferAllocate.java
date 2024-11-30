package com.xiaobin.netty.c1;

import java.nio.ByteBuffer;

/**
 * @author Joy
 * @date: 2024/11/30 14:59
 * @description: 测试allocate  allocateDirect
 * Good Luck!!!
 */
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());
        /*
        class java.nio.HeapByteBuffer   Java堆内存 读写效率低,收到垃圾回收影响
        class java.nio.DirectByteBuffer   直接内存 读写效率高 (少一次拷贝)  不会受到垃圾回收影响
         */
    }

}
