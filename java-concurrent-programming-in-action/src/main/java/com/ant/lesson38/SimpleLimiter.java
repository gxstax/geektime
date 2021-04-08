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
public class SimpleLimiter {

    // 下一秒令牌产生时间
    long next = System.nanoTime();

    // 令牌产生间隔时间
    long interval = 1000_000_000;

    /**
     * <p>
     * 预占令牌
     * </p>
     * 
      * @param now
     * @return long
     */
    synchronized long reserve(long now) {
        // 如果请求在下一令牌产生时间之后，重新计算下一令牌产生时间
        if (now > next) {
            next = now;
        }
        // 令牌能够获取的时间
        long at = next;

        // 设置下一个令牌产生的时间
        next += interval;

        return Math.max(at, 0L);
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
