package com.xiaobin.nio.c1.bytebuffer;

import java.nio.ByteBuffer;

import static com.xiaobin.nio.c1.bytebuffer.ByteBufferUtil.debugAll;

/**
 * @author Joy
 * @date: 2024/11/30 15:37
 * @description: 测试
 * Good Luck!!!
 */
public class TestByteBufferExam {
    public static void main(String[] args) {
        /**
         * 网络上有多条数据发送给服务端,数据之间使用\n进行分割
         * 但由于某种原因在数据接收时,被进行了重新组合
         * Hello,world\n
         * I'm zhangsan\n
         * How are you?\n
         * 变成下面两个bytebuffer(粘包 半包)
         * Hello,world\nI'm zhangsan\nHo
         * w are you?\n
         *
         * 如何解决这个问题?????????????????
         */


        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);

    }

    private static void split(ByteBuffer source) {
        //切换到读模式
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                //bytebuffer容量计算 +1 减去起始位置
                int length = i + 1 - source.position();
                //存入新的 bytebuffer
                ByteBuffer target = ByteBuffer.allocate(length);

                //从 source 读  向 target写
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }

        //切换到写模式
        source.compact();
    }
}
