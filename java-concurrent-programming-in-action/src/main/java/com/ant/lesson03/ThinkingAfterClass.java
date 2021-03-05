package com.ant.lesson03;

/**
 * <p>
 * 课后思考题：
 * 下面的代码用 synchronized 修饰代码块来尝试解决并发问题，你觉得这个使用方式正确吗？
 * 有哪些问题呢？
 * 能解决可见性和原子性问题吗？
 * </p>
 *
 * @author Ant
 * @since 2021/3/5 10:07 上午
 */
public class ThinkingAfterClass {
    long value = 0L;

    long get() {
        synchronized (new Object()) {
            return value;
        }
    }

    void addOne() {
        synchronized (new Object()) {
            value += 1;
        }
    }

    // 1: 使用方式不正确
    // 2: 这里的 synchronized 每次加锁都加在了 new Object()
    //    相当于每次调用都重新加一把锁，所以这里保证不了可见性
    // 3: 不能解决可见性和原子性问题


    // 验证代码
    public static void calc() throws InterruptedException {
        final ThinkingAfterClass afterClass = new ThinkingAfterClass();

        for (int i = 0; i < 100; i++) {
            Thread th1 = new Thread(() -> {
                afterClass.addOne();
                System.out.println(Thread.currentThread().getName() + "--->" + afterClass.get());
            });
            th1.start();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        calc();
    }




}
