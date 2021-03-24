package com.ant.lesson17;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/3/18 9:29 下午
 */
public class Cache<K, V> {
    final Map<K, V> m = new HashMap<>();

    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    // 读锁
    final Lock rLock = rwl.readLock();
    // 写锁
    final Lock wLock = rwl.writeLock();

    // 读缓存
    V get(K key) {
        rLock.lock();
        try {
            return m.get(key);
        } finally {
            rLock.unlock();
        }
    }

    // 写缓存
    V put(K key, V value) {
        wLock.lock();
        try {
            return m.put(key, value);
        } finally {
            wLock.unlock();
        }
    }

}
