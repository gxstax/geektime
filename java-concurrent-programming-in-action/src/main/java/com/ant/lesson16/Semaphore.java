package com.ant.lesson16;

import java.util.Queue;

/**
 * <p>
 * 信号量模拟
 * </p>
 *
 * @author Ant
 * @since 2021/3/17 1:15 下午
 */
public class Semaphore {
    // 计数器
    int count;

    // 等待队列
    Queue queue;

    Semaphore(int c) {
        this.count = c;
    }

    void down() {
        this.count--;
        if (this.count < 0) {
            // 将当前线程插入到等待队列
            // 阻塞当前线程
        }
    }

    void up() {
        this.count++;
        if (this.count<=0) {
            // 移除等待队列中的某个线程T
            // 唤醒线程T
        }
    }
}

