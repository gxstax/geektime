package com.ant.lesson08;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 阻塞队列模拟线程
 * </p>
 *
 * @author Ant
 * @since 2021/3/11 9:52 上午
 */
public class BlockedQueue<T> {
    final Lock lock = new ReentrantLock();

    // 模拟队列已满标志
    private boolean queueFull = false;
    // 模拟队列空标志
    private boolean queueEmpty = false;

    // 条件变量：队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty = lock.newCondition();

    // 入队
    void enq(T x) throws InterruptedException {
        lock.lock();
        try {
            while(queueFull) {
                notEmpty.await();
            }
            // 入队操作
            // 入队后，通知可出队
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // 出队
    void deq() throws InterruptedException {
        lock.lock();
        try {
            while(queueEmpty) {
                // 等待队列不空
                notEmpty.await();
            }
            // 出队操作
            // 出队后，通知可入队
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

}
