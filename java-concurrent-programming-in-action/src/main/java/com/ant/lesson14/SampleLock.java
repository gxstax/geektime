package com.ant.lesson14;

/**
 * <p>
 * 模拟 ReentrantLock 简单的加锁原理
 * 其实主要是利用了volatile 的 Happpens-Before规则
 * </p>
 *
 * @author Ant
 * @since 2021/2/26 11:44 上午
 */
public class SampleLock {
    volatile int state;

    // 加锁
    void lock() {
        if (state == 0) {
            state = 1;
        }
    }


    // 解锁
    void unlock() {
        state = 0;
    }


    public static void main(String[] args) {

    }

}
