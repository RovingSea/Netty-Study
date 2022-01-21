package com.wu.study.netty.basic.network.nonBlocking;

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
            // 非阻塞模式
            server.configureBlocking(false);
            // 为服务器通道绑定端口
            server.bind(new InetSocketAddress(8080));
            // 用户存放连接的集合
            ArrayList<SocketChannel> channels = new ArrayList<>();
            // 循环接收连接
            while (true) {
                // 非阻塞，线程还会继续运行，如果没有建立连接，sc就会为null
                SocketChannel sc = server.accept();
                if (sc != null) {
                    log.debug("connected... {}", sc);
                    // 非阻塞模式
                    sc.configureBlocking(false);
                    channels.add(sc);
                }
                // 循环遍历集合中的连接
                for(SocketChannel channel : channels) {
                    // 处理通道中的数据
                    // 非阻塞，线程仍然会继续运行，如果没有得到数据，read会返回0
                    int read = channel.read(buffer);
                    if (read > 0) {
                        buffer.flip();
                        ByteBufferUtil.debugRead(buffer);
                        buffer.clear();
                        log.debug("after read... {}", channel);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

