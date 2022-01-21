package com.wu.study.netty.basic.network.blocking;

import com.wu.study.netty.basic.util.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author Haixin Wu
 * @date 2022/1/21 16:43
 * @since 1.0
 */
@Slf4j
public class ServerTest {
    public static void main(String[] args) {
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 获得服务器通道
        try(ServerSocketChannel server = ServerSocketChannel.open()) {
            // 为服务器通道绑定端口
            server.bind(new InetSocketAddress(8080));
            // 用户存放连接的集合
            ArrayList<SocketChannel> channels = new ArrayList<>();
            // 循环接收连接
            while (true) {
                log.debug("connecting...");
                // 没有连接时，会阻塞线程
                SocketChannel sc = server.accept();
                log.debug("connected... {}", sc);
                channels.add(sc);
                // 循环遍历集合中的连接
                for(SocketChannel channel : channels) {
                    log.debug("before read... {}", channel);
                    // 处理通道中的数据
                    // 当通道中没有数据可读时，会阻塞线程
                    channel.read(buffer);
                    buffer.flip();
                    ByteBufferUtil.debugRead(buffer);
                    buffer.clear();
                    log.debug("after read... {}", channel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

