package com.ant.lesson03;

import com.ant.lesson01.TestDemo;

/**
 * <p>
 * 用 synchronized 解决 count+=1 的问题
 * </p>
 *
 * @author Ant
 * @since 2021/3/4 1:03 下午
 */
public class SafeCalc {
    private long value = 0;

    long get() {
        return value;
    }

    /**
     * <p>
     * 使用 synchronized 修饰非静态方法，相当于对当前对象 this 加锁
     * </p>
     * 
      * @param 
     * @return void
     */
    private synchronized void addOne() {
        value += 1;
    }

    public static void calc() throws InterruptedException {
        final SafeCalc test = new SafeCalc();

        for (int i = 0; i < 10; i++) {
            Thread th1 = new Thread(() -> {
                test.addOne();
                System.out.println(Thread.currentThread().getName() + "--->" + test.get());
            });
            th1.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        calc();
    }

}
