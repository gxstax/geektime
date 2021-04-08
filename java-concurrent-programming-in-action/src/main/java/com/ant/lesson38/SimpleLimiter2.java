package com.ant.lesson38;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 简单限流器
 * </p>
 *
 * @author Ant
 * @since 2021/4/7 9:24 上午
 */
public class SimpleLimiter2 {
    // 当前令牌桶中的令牌数量
    long storedPermits = 0;

    // 令牌桶容量
    long maxPermits = 3;

    // 下一秒令牌产生时间
    long next = System.nanoTime();

    // 令牌产生间隔时间
    long interval = 1000_000_000;

    void reSync(long now) {
        if (now > next) {
            // 新产生的令牌数
            long newPermits = (now - next) / interval;

            // 新令牌增加到令牌桶
            storedPermits = Math.max(newPermits + storedPermits, maxPermits);

            // 将下一个令牌发放时间重置为当前时间
            next = now;
        }
    }

    /**
     * <p>
     * 预占令牌
     * </p>
     * 
      * @param now
     * @return long
     */
    synchronized long reserve(long now) {
        reSync(now);

        // 令牌能够获取的时间
        long at = next;

        // 令牌桶中能提供的令牌
        long fb = Math.min(1, storedPermits);

        // 令牌净需求：首先减掉令牌桶中的令牌
        long nr = 1 - fb;

        // 重新计算下一个令牌产生时间
        next =  next + nr*interval;

        // 重新计算令牌桶中的令牌
        this.storedPermits -= fb;
        return at;
    }

    /**
     * <p>
     * 申请令牌
     * </p>
     * 
      * @param 
     * @return void
     */
    void acquire() {
        // 申请令牌的时间
        long now = System.nanoTime();

        // 预占令牌
        long at = reserve(now);

        long waitTime = Math.max(at - now, 0L);
        // 判断是否需要等待令牌
        try {
            TimeUnit.NANOSECONDS.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
