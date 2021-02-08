package com.ant.lesson25;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/2/8 5:54 下午
 */
public class CompletionServiceDemo {

    public static void main(String[] args) throws Exception {
        CompletionServiceDemo demo = new CompletionServiceDemo();
        demo.callPrice();
    }

    public void callPrice() throws Exception{
        long startTime = System.currentTimeMillis();

        ExecutorService excutor =
                new ThreadPoolExecutor(3, 10, 6, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(10));


        CompletionService<Integer> cs = new ExecutorCompletionService<>(excutor);

        cs.submit(()-> getPriceByS1());
        cs.submit(()-> getPriceByS2());
        cs.submit(()-> getPriceByS3());

        for (int i = 0; i < 3; i++) {
            Future<Integer> take = cs.take();
            excutor.execute(() -> {
                try {
                    save(take.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }

        excutor.shutdown();

        long endTime = System.currentTimeMillis();

        System.out.printf("处理耗时 %s ms", endTime - startTime);

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
