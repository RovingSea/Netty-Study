package com.wu.study.netty.introductory.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

import static com.wu.study.netty.introductory.utils.ByteBufLogUtil.log;

/**
 * @author Haixin Wu
 * @date 2022/1/27 16:55
 * @since 1.0
 */
public class ByteBufCompositeTest {
    public static void main(String[] args) {
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{'a', 'b', 'c', 'd', 'e'});
        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{'f', 'g', 'h', 'i', 'j'});

        CompositeByteBuf buffs = ByteBufAllocator.DEFAULT.compositeBuffer();
        buffs.addComponents(true, buf1, buf2);
        log(buffs);
    }
}

