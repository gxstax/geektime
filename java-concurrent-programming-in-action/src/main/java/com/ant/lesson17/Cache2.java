package com.ant.lesson17;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * 实现缓存的按需加载
 * </p>
 *
 * @author Ant
 * @since 2021/3/18 9:43 下午
 */
public class Cache2<K, V> {
    final Map<K, V> m = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    // 读锁
    final Lock rLock = lock.readLock();
    // 写锁
    final Lock wLock = lock.writeLock();

    V get(K key) {
        V v = null;
        rLock.lock();
        try {
            v = m.get(key);
        } finally {
            rLock.unlock();
        }

        if (null != v) {
            return v;
        }

        wLock.lock();
        try {
            /**
             * 另外，还需要注意的是，在获取写锁之后，我们并没有直接去查询数据库，而是在这里，
             * 重新验证了一次缓存中是否存在，
             * 再次验证如果还是不存在，我们才去查询数据库并更新本地缓存。为什么我们要再次验证呢？
             *
             * 原因是在高并发的场景下，有可能会有多线程竞争写锁。
             * 假设缓存是空的，没有缓存任何东西，
             * 如果此时有三个线程 T1、T2 和 T3 同时调用 get() 方法，并且参数 key 也是相同的。
             * 那么它们会同时执行到代码wLock.lock()处，但此时只有一个线程能够获得写锁，假设是线程 T1，线程 T1 获取写锁之后查询数据库并更新缓存，
             * 最终释放写锁。此时线程 T2 和 T3 会再有一个线程能够获取写锁，假设是 T2，如果不采用再次验证的方式，此时 T2 会再次查询数据库。
             * T2 释放写锁之后，T3 也会再次查询一次数据库。而实际上线程 T1 已经把缓存的值设置好了，T2、T3 完全没有必要再次查询数据库。
             * 所以，再次验证的方式，能够避免高并发场景下重复查询数据的问题。
             */
            v = m.get(key);
            if (null != v) {
                return v;
            }
            // 这里模拟从数据库中加载数据
            v = null;
            m.put(key, v);
        } finally {
            wLock.unlock();
        }

        return v;
    }


}
