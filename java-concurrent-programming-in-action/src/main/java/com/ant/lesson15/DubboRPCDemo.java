package com.ant.lesson15;

import org.apache.catalina.connector.Response;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * dubbo框架异步转同步
 * </p>
 *
 * @author GaoXin
 * @since 2021/3/16 1:01 下午
 */
public class DubboRPCDemo {

    private HttpServletResponse response;

    // 创建锁与条件变量
    private final Lock lock = new ReentrantLock();
    // 变量
    private final Condition done = lock.newCondition();

    // 调用方通过该方法等待结果
    Object get(int timeout) throws TimeoutException, InterruptedException {
        long start = System.nanoTime();
        lock.lock();
        try {
            while (!isDone()) {
                done.await();
                long cur = System.nanoTime();
                if (isDone() || cur-start > timeout) {
                    break;
                }
            }
        } finally {
            lock.unlock();
        }

        if (!isDone()) {
            throw new TimeoutException();
        }
        return response;
    }

    // 判断RPC结果是否已经返回
    boolean isDone() {
        return response != null;
    }

    // RPC 结果返回时调用该方法
    private void doReceived(Response res) {
        lock.lock();
        try {
            response = res;
            if (done != null) {
                done.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
