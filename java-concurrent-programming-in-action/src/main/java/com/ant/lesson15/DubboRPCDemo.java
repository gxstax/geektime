package com.ant.lesson15;

import org.apache.catalina.connector.Response;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
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

    // 变量（）
    private final Condition done = lock.newCondition();

    // 调用方通过该方法等待结果
    Object get(int timeout) throws TimeoutException, InterruptedException {
        long start = System.currentTimeMillis();
        lock.lock();
        try {
            System.out.println("get() 获取到锁....");
            while (!isDone()) {
                done.await(timeout, TimeUnit.MILLISECONDS);
                long cur = System.currentTimeMillis();
                if (isDone() || cur-start > timeout) {
                    break;
                }
                System.out.println("get() 执行完了 done.await()");
            }
        } finally {
            System.out.println("get() 执行了unlock");
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
        final boolean locked = lock.tryLock();
        try {
            if (locked) {
                System.out.println("doReceived() 获取到锁....");
                response = res;
                if (done != null) {
                    done.signal();
                }
            }
        } finally {
            System.out.println("doReceived() 释放锁....");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException, TimeoutException {
        DubboRPCDemo demo = new DubboRPCDemo();
        Thread thread1 = new Thread(() -> {
            try {
                demo.get(500);
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            demo.doReceived(new Response());
        });

        thread2.start();
    }

}
