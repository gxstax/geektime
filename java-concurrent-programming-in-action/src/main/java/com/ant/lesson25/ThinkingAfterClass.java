package com.ant.lesson25;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 课后思考题
 * </p>
 *
 * @author Ant
 * @since 2021/2/22 5:03 下午
 */
public class ThinkingAfterClass {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThinkingAfterClass demo = new ThinkingAfterClass();
        AtomicReference<Integer> integerAtomicReference = demo.callPrice();
        System.out.println("结果:" + integerAtomicReference);

    }

    private AtomicReference<Integer> callPrice() throws InterruptedException, ExecutionException {
        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 创建CompletionService
        CompletionService<Integer> cs = new
                ExecutorCompletionService<>(executor);
        // 异步向电商S1询价
        cs.submit(() -> getPriceByS1());
        // 异步向电商S2询价
        cs.submit(() -> getPriceByS2());
        // 异步向电商S3询价
        cs.submit(() -> getPriceByS3());

        // 将询价结果异步保存到数据库
        // 并计算最低报价
        AtomicReference<Integer> m =
                new AtomicReference<>(Integer.MAX_VALUE);

        for (int i = 0; i < 3; i++) {
            Integer r = cs.take().get();
            executor.execute(() -> {
                save(r);
                Integer finalR = r;
            });
            m.set(Integer.min(m.get(), r));
        }
        return m;
    }

    public Integer getPriceByS1() throws InterruptedException {
        System.out.printf("%s -- getPriceByS1...\n", Thread.currentThread().getName());
        // 设置一个处理延时
        Thread.sleep(1000L);
        return 1;
    }

    public Integer getPriceByS2() {
        System.out.printf("%s -- getPriceByS2...\n", Thread.currentThread().getName());
        return 2;
    }

    public Integer getPriceByS3() {
        System.out.printf("%s -- getPriceByS3...\n", Thread.currentThread().getName());
        return 3;
    }

    public void save(Object object) {
        System.out.printf("%s -- 保存--> %s \n", Thread.currentThread().getName(), object);
    }

}
