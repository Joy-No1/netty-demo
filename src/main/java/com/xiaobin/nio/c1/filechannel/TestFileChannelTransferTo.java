package com.xiaobin.nio.c1.filechannel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author Joy
 * @date: 2024/11/30 15:54
 * @description: 文件编程
 * Good Luck!!!
 */
public class TestFileChannelTransferTo {
    public static void main(String[] args) {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("to.txt").getChannel()
        ) {
            //效率高 底层会利用操作系统的零拷贝进行优化 2g数据
            long size = from.size();
            for (long left = size; left > 0; ) {
                System.out.println("position:"+(size-left)+"left:"+left);
                left -= from.transferTo(size - left, left, to);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
