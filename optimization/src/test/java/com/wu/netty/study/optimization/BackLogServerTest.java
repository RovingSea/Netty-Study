package com.wu.netty.study.optimization;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.AbstractNioChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.channels.SelectionKey;

/**
 * 关键断点:{@link io.netty.channel.nio.NioEventLoop#processSelectedKey(SelectionKey, AbstractNioChannel)}<br>
 * 第721行
 * @author Haixin Wu
 * @date 2022/2/13 20:47
 * @since 1.0
 */
public class BackLogServerTest {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                // 全队列阈值，因为netty处理速度较快，较难模拟，所以可以通过断点的方式进行
                .option(ChannelOption.SO_BACKLOG, 2)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler());
                    }
                }).bind(8080);
    }
}

