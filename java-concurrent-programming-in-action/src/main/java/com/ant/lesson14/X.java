package com.ant.lesson14;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/3/15 9:55 上午
 */
public class X {
    private final Lock rtl = new ReentrantLock();

    int value;

    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value += 1;
        } finally {
            // 释放锁
            rtl.unlock();
        }
    }

    public int get() {
        // 获取锁
        rtl.lock();
        try {
            return value;
        } finally {
            // 释放锁
            rtl.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        X x = new X();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    x.addOne();
                }
            });
            thread.start();

            // 为了等待线程结束后，才执行输出value
            thread.join();
        }



        System.out.println(x.value);
    }
}
