package com.wu.study.netty.introductory.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author Haixin Wu
 * @date 2022/1/26 21:10
 * @since 1.0
 */
@Slf4j
public class NettyPromiseTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 准备 EventLoop 对象
        EventLoop eventLoop = new NioEventLoopGroup().next();
        // 2. 可以主动创建 promise 结果容器对象
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);
        // 3. 启动一个线程执行计算，计算完毕后向 promise 中填入结果
        new Thread(() -> {
            log.debug("开始计算");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.setSuccess(80);
        }).start();
        // 4. 接收结果的线程
        log.debug("等待结果");
        log.debug("结果是：{}", promise.get());
    }
}

