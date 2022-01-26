package com.wu.study.netty.introductory.future;

import java.util.concurrent.*;

/**
 * @author Haixin Wu
 * @date 2022/1/26 20:48
 * @since 1.0
 */
public class JDKFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 2.提交任务
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("执行计算");
            Thread.sleep(1000);
            return 50;
        });
        // 3.主线程通过 future 来获取结果
        System.out.println("等待结果");
        System.out.println("结果是：" + future.get());
    }
}

