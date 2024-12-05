package com.xiaobin.nio.c1.bytebuffer;

import java.nio.ByteBuffer;

/**
 * @author Joy
 * @date: 2024/11/30 14:48
 * @description: 测试ByteBufferReadWrite
 * Good Luck!!!
 */
public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);  //'a'
        ByteBufferUtil.debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64});
        ByteBufferUtil.debugAll(buffer);
//        System.out.println(buffer.get());
        buffer.flip();//切换到读模式
        System.out.println(buffer.get());
        ByteBufferUtil.debugAll(buffer);

        buffer.compact();
        ByteBufferUtil.debugAll(buffer);

        buffer.put(new byte[]{0x65, 0x66});
        ByteBufferUtil.debugAll(buffer);

    }

}
