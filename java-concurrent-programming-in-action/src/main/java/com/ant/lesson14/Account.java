package com.ant.lesson14;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 课后思考题：
 * 你已经知道 tryLock() 支持非阻塞方式获取锁，下面这段关于转账的程序就使用到了 tryLock()，你来看看，它是否存在死锁问题呢？
 *
 * 当两个账户相互为对方转账会出现活锁！
 * </p>
 *
 * @author Ant
 * @since 2021/3/15 1:05 下午
 */

class Account {
    private int balance;
    private final Lock lock
            = new ReentrantLock();
    // 转账
    void transfer(Account tar, int amt){
        while (true) {
            if(this.lock.tryLock()) {
                try {
                    if (tar.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                        } finally {
                            tar.lock.unlock();
                        }
                    }//if
                } finally {
                    this.lock.unlock();
                }
            }//if
        }//while
    }//transfer


    // 加随机等待时间处理
    void transferRight(Account tar, int amt) throws InterruptedException {
        while (true) {
            if(this.lock.tryLock(100, TimeUnit.MICROSECONDS)) {
                try {
                    if (tar.lock.tryLock(100, TimeUnit.MICROSECONDS)) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                        } finally {
                            tar.lock.unlock();
                        }
                    }//if
                } finally {
                    this.lock.unlock();
                }
            }//if
        }//while
    }//transfer
}
