package com.wu.study.netty.basic;

import org.junit.Test;

import java.nio.ByteBuffer;

import static com.wu.study.netty.basic.util.ByteBufferUtil.debugAll;

/**
 * @author Haixin Wu
 * @date 2022/1/12 17:09
 * @since 1.0
 */
public class ByteBufferReadTest {
    @Test
    public void read(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd', 'e'});
        buffer.flip();
        // 从头开始读
        buffer.get(new byte[4]);
        debugAll(buffer);
        buffer.rewind();
        debugAll(buffer);
        System.out.println("buffer.get(3) = " + (char) buffer.get(3)); // 不会改变position的位置
        debugAll(buffer);
    }

    @Test
    public void MarkAndReset(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd', 'e'});
        buffer.flip();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        buffer.mark();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        buffer.reset();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
    }
}

