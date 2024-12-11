package com.xiaobin.protocol;

import com.xiaobin.message.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Joy
 * @date: 2024/12/11 20:38
 * @description: 测试编码解码
 * Good Luck!!!
 */

@Slf4j
public class TestMessageCodec {
    public static void main(String[] args) throws Exception {

        LengthFieldBasedFrameDecoder FRAME_DECODE = new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0);
        LoggingHandler LOGIN_HANDLER = new LoggingHandler();

        //encode
        EmbeddedChannel channel = new EmbeddedChannel(
                FRAME_DECODE,
                LOGIN_HANDLER,
                new MessageCodec()
        );

        LoginRequestMessage message = new LoginRequestMessage("zhangsan", "123");
        channel.writeOutbound(message);

        //decode
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buf);

        ByteBuf s1 = buf.slice(0, 100);
        ByteBuf s2 = buf.slice(100, buf.readableBytes() - 100);
        s1.retain();//引用计数+1
        channel.writeInbound(s1);  //release 引用计数减到0
        channel.writeInbound(s2);


        //入站
//        channel.writeInbound(buf);


    }
}
