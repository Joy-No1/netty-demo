package com.xiaobin.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

import static com.xiaobin.netty.c4.TestByteBuf.log;

/**
 * @author Joy
 * @date: 2024/12/8 21:48
 * @description: CompositeByteBuf
 * Good Luck!!!
 */
public class TestCompositeByteBuf {
    public static void main(String[] args) {

        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{1, 2, 3, 4, 5});
        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf2.writeBytes(new byte[]{6, 7, 8, 9, 10});

        CompositeByteBuf buffer = ByteBufAllocator.DEFAULT.compositeBuffer();
        buffer.addComponents(true,buf1, buf2);
        log(buffer);
    }
}
