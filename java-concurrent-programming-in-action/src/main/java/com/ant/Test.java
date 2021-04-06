package com.ant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/2/23 4:06 下午
 */
public class Test {
    // 创建线程池
    public final static ExecutorService executor =
            Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future> futures = new ArrayList<>();

//        List<Integer> results

        // 活动1
        Future<?> f1 = activity1();
        futures.add(f1);

        // 活动2
        Future<?> f2 = activity2();
        futures.add(f2);

        // 活动3
        Future<?> f3 = activity3();
        futures.add(f3);

        // 活动4
        Future<?> f4 = activity4();
        futures.add(f4);


        long start = System.currentTimeMillis();
        List<Integer> results = futures.stream().map(future -> {
            Integer i = null;
            try {
                i = (Integer) future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return i;
        }).collect(Collectors.toList());

        System.out.println(results);
        long end = System.currentTimeMillis();

        System.out.println(end - start);




//        Integer i1 = (Integer) f1.get();
//        System.out.println("------>" + i1);
//
//        Integer i2 = (Integer) f2.get();
//        System.out.println("------>" + i2);
//
//        Integer i3 = (Integer) f3.get();
//        System.out.println("------>" + i3);
//
//        Integer i4 = (Integer) f4.get();
//        System.out.println("------>" + i4);
//
//
//        System.out.println("end");

    }


    public static Future<Integer> activity1() {
        return executor.submit(() -> {
            sleep(6, TimeUnit.SECONDS);
            System.out.println("活动1执行完成！");
            return 1;
        });

    }

    public static Future<Integer> activity2() {
        return executor.submit(() -> {
            sleep(1, TimeUnit.SECONDS);
            System.out.println("活动2执行完成！");
            return 2;
        });
    }

    public static Future<Integer> activity3() {
        return executor.submit(() -> {
            sleep(3, TimeUnit.SECONDS);
            System.out.println("活动3执行完成！");
            return 3;
        });

    }

    public static Future<Integer> activity4() {
        return executor.submit(() -> {
            sleep(5, TimeUnit.SECONDS);
            System.out.println("活动4执行完成！");
            return 4;
        });

    }

    /**
     * <p>
     * 睡眠
     * </p>
     * 
      * @param t
     * @param unit
     * @return void
     */
    public static void sleep(int t, TimeUnit unit) {
        try {
            unit.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
