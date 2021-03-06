package com.ant.lesson05;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * <p>
 * 账户转账示例（一次性拿到所有需要的资源）
 * </p>
 *
 * @author Ant
 * @since 2021/3/8 12:20 下午
 */
public class Account {

    private int id;
    
    private int balance;

    // 这里的资源分配器需要是单例的
    private Allocator allocator;

    /**
     * <p>
     * 转账（一次性拿到所有需要的资源）
     * 缺点：容易造成死锁 （比如 A-B 转 100，B->A 转 100，
     * 当两个线程都执行到 synchronized (this) 这里的时候，
     * 一个等待B账户，一个等待A账户，造成死锁）
     * </p>
     * 
     * @param targetAccount 目标账户
     * @param amt 转账金额
     * @return
     */
    void transfer1(Account targetAccount, int amt) {
        // 锁定转出账户
        synchronized (this) {
            // 锁定转入账户
            synchronized (targetAccount) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    targetAccount.balance += balance;
                }
            }
        }
    }

    /**
     * <p>
     * 解决死锁1（方法1：破坏'占用且等待'条件）
     * 引入账本管理对象
     * </p>
     * 
      * @param 
     * @return void
     */
    void transferDeadLockSolve1(Account targetAccount, int amt) {
        // 一次性申请所有资源，直到申请成功，要不就一直等待
        while(allocator.apply(this, targetAccount));

        try {
            // 锁定转出账户
            synchronized (this) {
                // 锁定转入账户
                synchronized (targetAccount) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        targetAccount.balance += balance;
                    }
                }
            }
        } finally {
            allocator.free(this, targetAccount);
        }
    }

    /**
     * <p>
     * 解决死锁2（方法2：破坏'不可抢占'条件）
     * </p>
     * 
      * @param targetAccount 目标账户
     * @param amt
     * @return void
     */
    void transferDeadLockSolve2(Account targetAccount, int amt) {
        // synchronized 不支持，后面学到 Lock 的时候再来补全
    }

    /**
     * <p>
     * 解决死锁3 （方法3： 破坏'循环等待'条件）
     * 给资源编号，每次申请都从小到大申请
     * </p>
     * 
      * @param targetAccount
     * @param amt
     * @return void
     */
    void transferDeadLockSolve3(Account targetAccount, int amt) {
        Account left = this;
        Account right = targetAccount;

        if (this.id > targetAccount.id) {
            left = targetAccount;
            right = this;
        }

        // 锁定序号小的账户
        synchronized (left) {
            // 锁定序号大账户
            synchronized (right) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    targetAccount.balance += balance;
                }
            }
        }
    }

}
