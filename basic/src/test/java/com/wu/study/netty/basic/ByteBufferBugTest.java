package com.wu.study.netty.basic;

import com.wu.study.netty.basic.util.ByteBufferUtil;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * <li>粘包：发送方在发送数据时，并不是一条一条地发送数据，而是将数据整合在一起，当数据达到一定的数量后再一起发送。<br>
 * 这就会导致多条信息被放在一个缓冲区中被一起发送出去
 * <li>半包：接收方的缓冲区的大小是有限的，当接收方的缓冲区满了以后，就需要将信息截断，等缓冲区空了以后再继续放入数据。<br>
 * 这就会发生一段完整的数据最后被截断的现象
 *
 * <br>
 * <br>
 * <br>
 * 例如：
 * <pre>
 *     <li>Hello,world\n
 *     <li>I’m Masker\n
 *     <li>How are you?\n
 * </pre>
 * <br>
 * 最终变成了：
 * <pre>
 *     <li>Hello,world\nI’m Masker\nHo
 *     <li>w are you?\n
 * </pre>
 *
 *
 *
 * @author Haixin Wu
 * @date 2022/1/20 20:57
 * @since 1.0
 */
public class ByteBufferBugTest {

    @Test
    public void testBug() {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        // 模拟粘包+半包
        buffer.put("Hello,world\nI'm Masker\nHo".getBytes());
        // 调用split函数处理
        split(buffer);
        buffer.put("w are you?\n".getBytes());
        split(buffer);
    }

    private void split(ByteBuffer buffer) {
        // 切换为读模式
        buffer.flip();
        for(int i = 0; i < buffer.limit(); i++) {

            // 遍历寻找分隔符
            // get(i)不会移动position
            if (buffer.get(i) == '\n') {
                // 缓冲区长度
                int length = i+1-buffer.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                // 将前面的内容写入target缓冲区
                for(int j = 0; j < length; j++) {
                    // 将buffer中的数据写入target中
                    target.put(buffer.get());
                }
                // 打印查看结果
                ByteBufferUtil.debugAll(target);
            }
        }
        // 切换为写模式，但是缓冲区可能未读完，这里需要使用compact
        buffer.compact();
    }
}

