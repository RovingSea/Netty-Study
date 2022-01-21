package com.wu.study.netty.basic.network.blocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author Haixin Wu
 * @date 2022/1/21 16:43
 * @since 1.0
 */
public class ClientTest {
    public static void main(String[] args) {
        try (SocketChannel sc = SocketChannel.open()) {
            // 建立连接
            sc.connect(new InetSocketAddress("localhost", 8080));
            System.out.println("waiting...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

