package com.wu.study.netty.introductory.eventLoop;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author Haixin Wu
 * @date 2022/1/25 21:25
 * @since 1.0
 */
@Slf4j
public class TimingTaskTest {
    public static void main(String[] args) {
        // DefaultEventLoopGroup group1 = new DefaultEventLoopGroup(); 普通，定时任务

        // io 事件，普通，定时任务
        NioEventLoopGroup group = new NioEventLoopGroup(2);
        // next方法实现了一个轮询，达到负载均衡效果
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        // 执行定时任务，异步处理，每隔 1 秒打印一个 ok
        group.next().scheduleAtFixedRate(() -> {
            log.debug("ok");
        }, 0, 1, TimeUnit.SECONDS);
        log.debug("main");
    }
}

