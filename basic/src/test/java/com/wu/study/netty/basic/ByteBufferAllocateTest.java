package com.wu.study.netty.basic;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author Haixin Wu
 * @date 2022/1/12 15:20
 * @since 1.0
 */
public class ByteBufferAllocateTest {
    @Test
    public void allocate(){
        /**
         * ByteBuffer.allocate(16).getClass() = class java.nio.HeapByteBuffer -> HeapByteBuffer <br>
         * java堆内存，读写效率较低，会受到 GC 的影响，为了使内存紧凑，所以会使用复制算法
         * ByteBuffer.allocateDirect(16).getClass() = class java.nio.DirectByteBuffer -> DirectByteBuffer <br>
         * 直接内存，读写效率高（原因是少一次拷贝），使用系统内存，不会受 GC 影响； 分配的效率低，可能会出现内存泄露
         */
        System.out.println("ByteBuffer.allocate(16).getClass() = " + ByteBuffer.allocate(16).getClass());
        System.out.println("ByteBuffer.allocateDirect(16).getClass() = " + ByteBuffer.allocateDirect(16).getClass());
    }
}

