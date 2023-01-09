package com.ant.patterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 单例（枚举）
 * 优点：通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
 * </p>
 *
 * @author Ant
 * @since 2020/02/09 7:46 下午
 */
public enum IdGeneratorByEnum {
    INSTANCE;
    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
