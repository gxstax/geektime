package com.ant.lesson12;

/**
 * <p>
 * 计数器
 * </p>
 *
 * @author Ant
 * @since 2021/3/13 6:27 下午
 */
public class Counter {
    private long value;

    synchronized long get() {
        return value;
    }

    synchronized long addOne() {
        return value++;
    }
}
