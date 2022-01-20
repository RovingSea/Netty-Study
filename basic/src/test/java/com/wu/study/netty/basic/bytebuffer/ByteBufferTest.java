package com.wu.study.netty.basic.bytebuffer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Haixin Wu
 * @date 2022/1/12 14:12
 * @since 1.0
 */
@Slf4j
public class ByteBufferTest {
    @Test
    public void Test() {
        // FileChannel 可以从输入输出流、RandomAccessFile中获取
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            // 从 channel 读取数据，就是向缓冲区中写数据
            channel.read(buffer);
            // 将缓冲区切换至读模式
            buffer.flip();
            while (buffer.hasRemaining()) { //打印 buffer 中的内容
                byte b = buffer.get();
                System.out.println((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test1() {
        // FileChannel 可以从输入输出流、RandomAccessFile中获取
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            while (true) {
                // 准备缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(10);
                // 从 channel 读取数据，就是向缓冲区中写数据
                int len = channel.read(buffer);
                log.debug("读取到的字节数{}", len);
                if (len == -1) { // 读完了，已经没有内容了
                    break;
                }
                // 将缓冲区切换至读模式
                buffer.flip();
                while (buffer.hasRemaining()) { //打印 buffer 中的内容
                    byte b = buffer.get();
                    log.debug("当前字节{}", (char) b);
                }
                // 将缓冲区切换至写模式
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

