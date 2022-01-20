package com.wu.study.netty.basic.bytebuffer;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 集中写
 * @author Haixin Wu
 * @date 2022/1/20 17:14
 * @since 1.0
 */
public class ByteBufferGatheringWriteTest {
    @Test
    public void gatheringWrite() {
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer buffer3 = StandardCharsets.UTF_8.encode("你好");// 汉字占3个字节
        try (FileChannel channel = new RandomAccessFile("ByteBufferGatheringWriteTest.txt", "rw").getChannel()) {
            channel.write(new ByteBuffer[]{buffer1, buffer2, buffer3});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

