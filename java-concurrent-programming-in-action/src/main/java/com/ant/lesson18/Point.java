package com.ant.lesson18;


import java.util.concurrent.locks.StampedLock;

/**
 * <p>
 * JDK 提供示例
 * </P>
 *
 * @author Ant
 * @since 2022/12/28 13:30
 **/

class Point {
    private int x, y;
    final StampedLock sl = new StampedLock();

    //计算到原点的距离
    double distanceFromOrigin() {
        // 乐观读
        long stamp = sl.tryOptimisticRead();
        // 读入局部变量，
        // 读的过程数据可能被修改
        int curX = x, curY = y;
        //判断执行读操作期间，
        //是否存在写操作，如果存在，
        //则sl.validate返回false
        if (!sl.validate(stamp)){
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(curX * curX + curY * curY);
    }
}
