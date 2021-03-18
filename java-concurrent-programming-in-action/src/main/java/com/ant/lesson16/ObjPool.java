package com.ant.lesson16;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * <p>
 * 对象池限流
 * </p>
 *
 * @author Ant
 * @since 2021/3/18 12:42 下午
 */
public class ObjPool<T, R> {
    final List<T> pool;

    // 用信号量实现限流器
    final Semaphore sem;

    // 构造函数
    public ObjPool(int size, T t) {
        pool = new Vector<T>() {
        };
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    // 利用对象池的对象，调用func
    R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }

    private static String func(Long aLong) {
        return String.valueOf(aLong);
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建对象池
        ObjPool<Long, String> pool = new ObjPool<Long, String>(10, 2L);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 通过对象池获取t，之后执行
                    try {
                        System.out.println(pool.exec(ObjPool::func));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }


    }


}
