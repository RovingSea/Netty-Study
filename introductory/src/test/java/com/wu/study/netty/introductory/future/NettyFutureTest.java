package com.wu.study.netty.introductory.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * @author Haixin Wu
 * @date 2022/1/26 20:54
 * @since 1.0
 */
@Slf4j
public class NettyFutureTest {

    public static void main(String[] args) {
        async();
    }

    public static void sync() throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        Future<Integer> future = eventLoop.submit(() -> {
            System.out.println("执行计算");
            Thread.sleep(1000);
            return 70;
        });
        System.out.println("等待结果");
        System.out.println("结果是：" + future.get());
    }


    public static void async() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        Future<Integer> future = eventLoop.submit(() -> {
            log.debug("执行计算");
            Thread.sleep(1000);
            return 70;
        });
        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.debug("结果是：" + future.get());
            }
        });
    }
}

