package com.xiaobin.netty.filechannel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Joy
 * @date: 2024/11/30 16:37
 * @description: 测试文件拷贝
 * Good Luck!!!
 */
public class TestFileCopy {
    public static void main(String[] args) throws Exception {
        String source = "";
        String target = "";

        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetName = path.toString().replace(source, target);
                if (Files.isDirectory(path)) {
                    //是文件夹
                    Files.createDirectory(Paths.get(targetName));
                } else if (Files.isRegularFile(path)) {
                    //普通文件
                    Files.copy(path, Paths.get(targetName)); {

                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
