package com.ant.lesson01;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/3/3 1:10 下午
 */
public class TestDemo {
    private static long count = 0;

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public static long calc() throws InterruptedException {
        final TestDemo test = new TestDemo();

        Thread th1 = new Thread(() -> {
            test.add10K();
        });

        Thread th2 = new Thread(() -> {
            test.add10K();
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());
    }
}
