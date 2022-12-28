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
    private String name;
    private final Lock lock
            = new ReentrantLock();

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    // 转账
    void transfer(Account tar, int amt){
        while (true) {
            System.out.println(Thread.currentThread().getId() + "执行。。。。。。");
            if(this.lock.tryLock()) {
                try {
                    if (tar.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                            System.out.println(Thread.currentThread().getId() + " " + this.name + "给" + tar.name + "转账成功");
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

    public static void main(String[] args) {
        Account account1 = new Account("张三",100);
        Account account2 = new Account("李四", 100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                account1.transfer(account2, 2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                account2.transfer(account1, 2);
            }
        }).start();
    }
}
