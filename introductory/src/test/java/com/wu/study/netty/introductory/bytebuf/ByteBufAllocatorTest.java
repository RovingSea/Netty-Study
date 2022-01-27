package com.wu.study.netty.introductory.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Test;

import static com.wu.study.netty.introductory.utils.ByteBufLogUtil.log;

/**
 * @author Haixin Wu
 * @date 2022/1/27 15:16
 * @since 1.0
 */
public class ByteBufAllocatorTest {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        log(buffer);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            stringBuilder.append("a");
        }
        buffer.writeBytes(stringBuilder.toString().getBytes());
        log(buffer);
    }

    @Test
    public void HeapAndDirectTest(){
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(16);
        System.out.println(buffer.getClass());

        buffer = ByteBufAllocator.DEFAULT.heapBuffer(16);
        System.out.println(buffer.getClass());

        buffer = ByteBufAllocator.DEFAULT.directBuffer(16);
        System.out.println(buffer.getClass());
    }
}

