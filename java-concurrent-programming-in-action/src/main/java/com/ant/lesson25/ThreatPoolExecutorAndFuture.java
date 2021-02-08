package com.ant.lesson25;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>
 * ThreatPoolExecutor + Future 方案实现实现烧水泡茶
 * </p>
 *
 * @author Ant
 * @since 2021/2/7 3:59 下午
 */
public class ThreatPoolExecutorAndFuture {
    public static void main(String[] args) throws Exception {
        ThreatPoolExecutorAndFuture threatPoolExecutorAndFuture = new ThreatPoolExecutorAndFuture();
        threatPoolExecutorAndFuture.makeTea();
    }

    public void makeTea() throws Exception {

        // 创建线程池
        ExecutorService executor =  Executors.newFixedThreadPool(3);

        // 异步向电商S1询价
        Future<?> f1 =
                executor.submit(
                        ()->getPriceByS1());

        // 异步向电商S2询价
        Future<Integer> f2 =
                executor.submit(
                        ()->getPriceByS2());

        // 异步向电商S3询价
        Future<Integer> f3 =
                executor.submit(
                        ()->getPriceByS3());

        // 获取电商S1报价并保存
        // (如果 异步向电商S1询价 这个执行特别慢的话，那么主线程会阻塞在这里)
        final Object r = f1.get();
        executor.execute(()->save(r));

        // 获取电商S2报价并保存
        final Object r2 = f2.get();
        executor.execute(()->save(r2));

        // 获取电商S3报价并保存
        final Object r3 = f3.get();
        executor.execute(()->save(r3));

        executor.shutdown();
    }


    public Integer getPriceByS1() throws InterruptedException {
        System.out.println("getPriceByS1...");
        // 设置一个处理延时
        Thread.sleep(1000L);
        return 1;
    }

    public Integer getPriceByS2() {
        System.out.println("getPriceByS2...");
        return 2;
    }

    public Integer getPriceByS3() {
        System.out.println("getPriceByS3...");
        return 3;
    }

    public void save(Object object) {
        System.out.printf("保存--> %s \n", object);
    }

}
