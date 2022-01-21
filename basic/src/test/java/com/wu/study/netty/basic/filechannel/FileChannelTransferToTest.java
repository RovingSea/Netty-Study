package com.wu.study.netty.basic.filechannel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 文件传输
 *
 * @author Haixin Wu
 * @date 2022/1/21 16:09
 * @since 1.0
 */
public class FileChannelTransferToTest {
    @Test
    public void transferToTest() {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("fileChannelTransferTo.txt").getChannel();
        ) {
            // 效率高，底层会利用操作系统的零拷贝进行优化，最大 2G
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 因为使用FileChannel最多只能穿2G，所以进行分批处理
     */
    @Test
    public void transferToTest1() {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("fileChannelTransferTo.txt").getChannel();
        ) {
            // 效率高，底层会利用操作系统的零拷贝进行优化，最大 2G
            long size = from.size();
            for (long left = size; left > 0;) {
                System.out.println("position:" + (size - left) + " left:" + left);
                left -= from.transferTo(size - left, from.size(), to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

