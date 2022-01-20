package com.wu.study.netty.basic.bytebuffer;

import com.sun.corba.se.impl.encoding.CDRInputObject;
import org.junit.Test;

import java.nio.ByteBuffer;

import static com.wu.study.netty.basic.util.ByteBufferUtil.debugAll;

/**
 * @author Haixin Wu
 * @date 2022/1/12 14:58
 * @since 1.0
 */
public class ByteBufferReadWriteTest {
    @Test
    public void put(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61); // a
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64}); // b c d
        debugAll(buffer);
//        System.out.println(buffer.get()); // 如果不切换读模式，get方法会得到position下标的值
        buffer.flip(); // 切换为读模式后，position会从0开始
        System.out.println(buffer.get());
        debugAll(buffer);
        buffer.compact(); // 把未读完的数据向前压缩，然后切换到写模式，数据前移后，原位置的值并未清零，写时会覆盖之前的值
        debugAll(buffer);
        buffer.put(new byte[]{0x65, 0x6f});
        debugAll(buffer);
    }
}

