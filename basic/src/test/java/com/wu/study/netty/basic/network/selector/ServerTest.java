package com.wu.study.netty.basic.network.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

import static com.wu.study.netty.basic.util.ByteBufferUtil.debugRead;

/**
 * @author Haixin Wu
 * @date 2022/1/22 14:03
 * @since 1.0
 */
@Slf4j
public class ServerTest {
    public static void main(String[] args) throws IOException {
        // 1. 创建 selector，管理多个 channel
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 2. 建立 selector 和 channel 的联系（注册）
        // selectionKey 就是将来事件发生后，通过它可以知道 事件 和 哪个channel的事件
        // 第二个参数 ops 为 0 时表示不关注任何事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // key 只关注 accept 事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("注册的ServerSocketChannel : {}", sscKey);
        // 3. 绑定端口
        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            // 4. select 方法, 没有事件就阻塞，有事件就会恢复
            // select 在事件未处理时，它不会阻塞
            selector.select();
            // 5. 处理事件
            // 5.1 拿到所有可用的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 5.2 遍历所有事件
            while (iterator.hasNext()) {
                System.out.println("删除前selectedKeys的长度 = " + selector.selectedKeys().size());
                SelectionKey key = iterator.next();
                // 5.3 处理 key 时，要从 selectedKeys 集合中删除，否则下次处理就会有问题
                iterator.remove();
                System.out.println("删除后selectedKeys的长度 = " + selector.selectedKeys().size());
                log.debug("SelectionKey: {}", key);
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey selectionKey = sc.register(selector, 0);
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    log.debug("SocketChannel: {}", sc);
                } else if (key.isReadable()) {
                    try {
                        SocketChannel sc = (SocketChannel) key.channel();
                        log.debug("SocketChannel: {}", sc);
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        int read = sc.read(buffer);// 如果是正常断开，read 方法的返回值是-1
                        if (read == -1) {
                            key.cancel();
                        } else {
                            buffer.flip();
                            debugRead(buffer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        // 因为客户端异常断开了，因此需要将 key 取消（从 selector 的 key 集合中删除真正的 key）
                        key.cancel();
                    }
                }

            }
        }
    }
}

