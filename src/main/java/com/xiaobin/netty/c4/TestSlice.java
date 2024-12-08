package com.xiaobin.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

import static com.xiaobin.netty.c4.TestByteBuf.log;

/**
 * @author Joy
 * @date: 2024/12/8 21:35
 * @description: Good Luck!!!
 */

@Slf4j
public class TestSlice {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes(new byte[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'});
        log(buf);

        //切片过程中没有发生数据赋值
        ByteBuf buf1 = buf.slice(0, 5);
        buf1.retain();
        ByteBuf buf2 = buf.slice(5, 5);
        log(buf1);
        log(buf2);

        buf.release();
        log(buf1);

        buf1.release();
        buf2.release();
      /*  log.debug("==========================================");
        buf1.setByte(0,'b');
        log(buf1);
        log(buf);*/

    }
}
