package com.xiaobin.nio.c4;

import com.xiaobin.nio.c1.bytebuffer.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Joy
 * @date: 2024/12/4 20:11
 * @description: 异步IO模拟
 * Good Luck!!!
 */

@Slf4j
public class AioFileChannel {
    public static void main(String[] args) throws IOException {
        try {
            AsynchronousFileChannel s = AsynchronousFileChannel.open(Paths.get("data.txt"), StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(2);
            log.debug("begin...");
            s.read(buffer, 0, null, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    log.debug("read completed...{}", result);
                    buffer.flip();
                    ByteBufferUtil.debugAll(buffer);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    log.debug("read failed...");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("do other things...");
        System.in.read();
    }
}
