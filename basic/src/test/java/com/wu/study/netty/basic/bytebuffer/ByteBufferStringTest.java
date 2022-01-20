package com.wu.study.netty.basic.bytebuffer;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.wu.study.netty.basic.util.ByteBufferUtil.debugAll;

/**
 * @author Haixin Wu
 * @date 2022/1/12 17:21
 * @since 1.0
 */
public class ByteBufferStringTest {
    /**
     * ByteBuffer 转换为字符串
     */
    @Test
    public void convertToByteBuffer(){
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        String s = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println("s = " + s);
    }
    /**
     * 字符串转为 ByteBuffer<br>
     * 使用完后仍然是写模式<br>
     * 如果使用这种方式转换为String，那么在转换之前要转换为读模式
     */
    @Test
    public void convertToString1(){
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());
        debugAll(buffer);
        String s = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println("没有转换为读模式之前：s = " + s);
        buffer.flip();
        String s1 = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println("转换为读模式之后: s1 = " + s1);
    }

    /**
     * 使用 CharSet<br>
     * 使用完自动切换为读模式
     */
    @Test
    public void convertToString2(){
        ByteBuffer buffer = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer);
    }

    /**
     * 使用 wrap<br>
     * 使用完自动切换为读模式
     */
    @Test
    public void convertToString3(){
        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer);
    }
}

