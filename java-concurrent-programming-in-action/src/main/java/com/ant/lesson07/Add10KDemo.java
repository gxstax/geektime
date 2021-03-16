package com.ant.lesson07;

/**
 * <p>
 * add10K 方法示例
 * </p>
 *
 * @author Ant
 * @since 2021/3/9 1:07 下午
 */
public class Add10KDemo {
    private long count = 0;

    synchronized long get() {
        return count;
    }

    synchronized void set(long v) {
        count = v;
    }

    void add10K() {
        int idx = 0;
        while(idx++ < 10000) {
            long v = get();
            set(v++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add10KDemo demo = new Add10KDemo();

        Thread th1 = new Thread(() -> {
            demo.add10K();
        });
        Thread th2 = new Thread(() -> {
            demo.add10K();
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println(demo.count);

    }
}
