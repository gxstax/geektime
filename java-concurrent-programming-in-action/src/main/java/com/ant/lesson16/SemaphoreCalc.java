package com.ant.lesson16;

import java.util.concurrent.Semaphore;

/**
 * <p>
 * 信号量保证累加的互斥性
 * </p>
 *
 * @author Ant
 * @since 2021/3/17 6:58 下午
 */
public class SemaphoreCalc {
    static int count;
    // 初始化信号量
    static final Semaphore s = new Semaphore(1);

    // 用信号量保证互斥
    static void addOne() throws InterruptedException {
        s.acquire();
        try {
            count += 1;
        } finally {
            s.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreCalc demo = new SemaphoreCalc();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        addOne();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();
        }

        System.out.println(count);

    }
}
