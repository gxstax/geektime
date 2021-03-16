package com.ant.lesson12;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/3/13 6:41 下午
 */
public class SafeWM {
    // 库存上限
    private final AtomicLong upper =
            new AtomicLong(0);

    // 库存下限
    private final AtomicLong lower =
            new AtomicLong(0);

    // 设置库存上限
    void setUpper(long v) {
        // 检查库存合法性
        if (v < lower.get()) {
            throw  new IllegalArgumentException();
        }
        upper.set(v);
    }

    // 设置库存下限
    void setLower(long v) {
        // 检查库存合法性
        if (v > upper.get()) {
            throw  new IllegalArgumentException();
        }
        lower.set(v);
    }
}
