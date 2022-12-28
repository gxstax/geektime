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
public class ObjPool<R> {
    final List<TBean> pool;

    // 用信号量实现限流器
    final Semaphore sem;

    // 构造函数
    public ObjPool(int size) {
        pool = new Vector<TBean>() {
        };
        for (int i = 0; i < size; i++) {
            TBean tBean = new TBean();
            pool.add(tBean);
        }
        sem = new Semaphore(size);
    }

    // 利用对象池的对象，调用func
    R exec(Function<TBean, R> func) throws InterruptedException {
        TBean t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }

    private String func(TBean t) {
        return String.valueOf(t.hashCode());
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建对象池
        ObjPool<String> pool = new ObjPool<String>(10);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 通过对象池获取t，之后执行
                    try {
                        System.out.println(pool.exec(pool::func));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }


    }


}
