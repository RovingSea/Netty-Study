package com.wu.study.netty.basic;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.wu.study.netty.basic.util.ByteBufferUtil.debugAll;

/**
 * 分散读
 * @author Haixin Wu
 * @date 2022/1/20 17:08
 * @since 1.0
 */
public class ByteBufferScatteringReadTest {
    @Test
    public void scatteringReadTest() {
        try (FileChannel channel = new RandomAccessFile("data.txt", "r").getChannel()) {
            ByteBuffer buffer1 = ByteBuffer.allocate(1);
            ByteBuffer buffer2 = ByteBuffer.allocate(3);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{buffer1, buffer2, buffer3});
            buffer1.flip();
            buffer2.flip();
            buffer3.flip();
            debugAll(buffer1);
            debugAll(buffer2);
            debugAll(buffer3);
        } catch (IOException ignored) {

        }
    }
}

