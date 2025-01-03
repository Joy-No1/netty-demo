package com.xiaobin.protocol;

import com.xiaobin.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author Joy
 * @date: 2024/12/11 20:08
 * @description: 自定义消息转换器
 * Good Luck!!!
 */

@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        //1.  4字节的魔术
        out.writeBytes(new byte[]{1, 2, 3, 4});

        //2.  1字节的版本
        out.writeByte(1);

        //3.  1字节的序列化方式  jdk 0, json
        out.writeByte(0);

        //4.  1字节的指令类型
        out.writeByte(msg.getMessageType());

        //5.  4个字节
        out.writeInt(msg.getSequenceId());

        //无意义  对齐填充1
        out.writeByte(0xff);


        //7.  获取内容的字节数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();

        //6.  长度
        out.writeInt(bytes.length);

        //8.  写入内容
        out.writeBytes(bytes);

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicNum = in.readInt();
        byte version = in.readByte();
        byte serializerType = in.readByte();
        byte messageType = in.readByte();
        int sequenceId = in.readInt();
        in.readByte();
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);
//        if (serializerType == 0) {
        //jdk的序列化方式
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message msg = (Message) ois.readObject();
        log.debug("{},{},{},{},{},{}", magicNum, version, serializerType, messageType, sequenceId, length);
        log.debug("msg:{}", msg);
        out.add(msg);
//        }
    }
}
