package com.ant.lesson25;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>
 * ThreatPoolExecutor + Future 方案实现实现烧水泡茶
 * </p>
 *
 * @author Ant
 * @since 2021/2/7 3:59 下午
 */

public class ThreatPoolExecutorAndFuture2 {

    static final Logger log = LoggerFactory.getLogger(ThreatPoolExecutorAndFuture2.class);


    public static void main(String[] args) throws Exception {
        ThreatPoolExecutorAndFuture2 threatPoolExecutorAndFuture = new ThreatPoolExecutorAndFuture2();
        threatPoolExecutorAndFuture.makeTea();
    }

    public void makeTea() throws ExecutionException, InterruptedException {

        // 创建阻塞队列
        BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();

        // 创建线程池
        ExecutorService executor =  Executors.newFixedThreadPool(3);

        // 异步向电商S1询价
        Future<Integer> f1 =
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

        // 获取电商S1报价
        executor.execute(()-> {
            try {
                bq.put(f1.get());
            } catch (Exception e) {
                log.error("putF1 error", e);
            }
        });

        // 获取电商S2报价
        executor.execute(()-> {
            try {
                bq.put(f2.get());
            } catch (Exception e) {
                log.error("putF2 error", e);
            }
        });

        // 获取电商S3报价
        executor.execute(()-> {
            try {
                bq.put(f3.get());
            } catch (Exception e) {
                log.error("putF3 error", e);
            }
        });

        // 异步保存所有报价
        for (int i = 0; i < 3; i++) {
            Integer take = bq.take();
            executor.execute(() -> save(take));
        }

        executor.shutdown();
    }


    public Integer getPriceByS1() throws InterruptedException {
        System.out.println("getPriceByS1...");
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
