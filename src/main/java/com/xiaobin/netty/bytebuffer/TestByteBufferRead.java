package com.xiaobin.netty.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author Joy
 * @date: 2024/11/30 15:07
 * @description: 测试读
 * Good Luck!!!
 */
public class TestByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();

        //从头开始读
/*        buffer.get(new byte[4]);
        ByteBufferUtil.debugAll(buffer);
        buffer.rewind();
        System.out.println((char) buffer.get());*/

        //mark & reset
        //mark做一个标记 记录position 位置 reset 是将position重置到mark位置
        /*System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.mark();
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        buffer.reset();//重置到索引2
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());*/

        //get(i)
        System.out.println((char) buffer.get(3));
        ByteBufferUtil.debugAll(buffer);

    }
}
