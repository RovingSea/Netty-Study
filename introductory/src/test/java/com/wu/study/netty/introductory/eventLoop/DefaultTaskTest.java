package com.wu.study.netty.introductory.eventLoop;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Haixin Wu
 * @date 2022/1/25 21:42
 * @since 1.0
 */
@Slf4j
public class DefaultTaskTest {
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
        // 执行普通任务，异步处理，提高工作效率
        group.next().submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("ok");
        });
        group.next().submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("ok");
        });

        log.debug("main");
    }
}

