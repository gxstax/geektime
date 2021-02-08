package com.ant.lesson25;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * CompletableFuture 示例
 * </p>
 *
 * @author Ant
 * @since 2021/1/4 10:09 上午
 */
public class CompletableFutureDemo {

   static ExecutorService executor = new ThreadPoolExecutor(
           Runtime.getRuntime().availableProcessors() + 1, Runtime.getRuntime().availableProcessors() * 2,
           30, TimeUnit.SECONDS,
           new ArrayBlockingQueue<>(1000),
           Executors.privilegedThreadFactory());

    public static void main(String[] args) {
        customerExecutors();
    }

    static void sleep ( int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }


    static void completableFutureDemo() {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });

        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });

        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf) -> {
                    System.out.println("T1:拿到茶叶:" + tf);
                    System.out.println("T1:泡茶...");
                    return "上茶:" + tf;
                });
        //等待任务3执行结果
        System.out.println(f3.join());
    }

    static void customerExecutors() {
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {
            System.out.println("c1 --> " + Thread.currentThread().getName());
        }, executor);

        CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("c2 --> " + Thread.currentThread().getName());
            sleep(2, TimeUnit.SECONDS);
            return "fuck you";
        }, executor);

        executor.shutdown();

//        try {
//            executor.shutdown();
//            executor.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
