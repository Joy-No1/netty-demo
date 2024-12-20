package com.xiaobin.nio.c1.bytebuffer;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Joy
 * @date: 2024/11/30 10:28
 * @description: 测试bytebuffer
 * Good Luck!!!
 */

@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        //TestFileChannelTransferTo
        //1 输入流/输出流
        //2 RandomAccessFile
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                //从channel读取数据 向 buffer 写入
                int len = channel.read(buffer);
                log.error("读取到的字节数:{}", len);
                if (len == -1) {
                    break;
                }
                //打印buffer内容
                buffer.flip();  //切换到读模式
                while (buffer.hasRemaining()) {//是否还有未读的字节
                    byte b = buffer.get();
                    log.error("读取到的字节:{}", (char) b);
                }
                //切换buffer为写模式
                buffer.clear();
            }
        } catch (Exception e) {
            log.error("发生异常", e);
        }
    }
}
