package com.ant.lesson31;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * <p>
 * "保护性地暂停"-实现方案
 * </p>
 *
 * @author Ant
 * @since 2021/3/26 7:24 下午
 */
public class GuardedObject<T> {
    // 受保护的对象
    T obj;
    final Lock lock = new ReentrantLock();

    final Condition done = lock.newCondition();

    final int timeout = 1;

    //保存所有 GuardedObject
    final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

    static <K> GuardedObject create(K key) {
        GuardedObject go = new GuardedObject();
        gos.put(key, go);
        return go;
    }

    static <K, T> void fireEvent(K key, T obj) {
        GuardedObject remove = gos.remove(key);
        if (null != remove) {
            remove.onChanged(obj);
        }
    }

    // 获取保护的对象
    T get(Predicate<T> p) {
        lock.lock();
        try {
            // MESA管程推荐写法
            while(!p.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        // 返回非空的受保护对象
        return obj;
    }
    
    /**
     * <p>
     * 事件通知方法
     * </p>
     * 
      * @param obj
     * @return void
     */
    void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
