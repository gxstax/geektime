package com.ant.lesson18;

import java.util.concurrent.locks.StampedLock;

/**
 * <p>
 * StampedLock 示例
 * </p>
 *
 * @author Ant
 * @since 2021/3/18 10:20 下午
 */
public class StampedLockDemo {

    final StampedLock sl = new StampedLock();




    /**
     * <p>
     * 获取/释放悲观锁示例代码
     * </p>
     * 
      * @param 
     * @return void
     */
    public void rLockGetAndReleaseLock() {
        // 写锁
        long rStamp = sl.readLock();
        try {
            // 业务代码.......
        } finally {
            sl.unlockRead(rStamp);
        }
    }

    /**
     * <p>
     * 获取释放写锁示例代码
     * </p>
     * 
      * @param 
     * @return void
     */
    public void wLockGetAndRelease() {
        long wStamp = sl.writeLock();
        try {
            // 业务代码
        } finally {
            sl.unlockWrite(wStamp);
        }
    }
    
}
