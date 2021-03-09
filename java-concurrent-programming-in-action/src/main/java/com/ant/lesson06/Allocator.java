package com.ant.lesson06;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 利用等待-通知机制优化 Allocator 类
 * </p>
 *
 * @author Ant
 * @since 2021/3/9 9:55 上午
 */
public class Allocator {
    // 已经被使用的资源列表
    private List<Object> als = new ArrayList<>();

    /**
     * <p>
     * 申请资源()
     * </p>
     *
     * @param
     * @return boolean
     */
    synchronized void apply(Object from, Object to) {
        // 只要需要的资源有一个被使用，那么申请资源就申请不了
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        als.add(from);
        als.add(to);
    }

    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);

        // 唤醒所有持有锁的线程
        notifyAll();
    }
}
