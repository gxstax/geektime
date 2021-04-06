package com.ant.lesson30;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/3/25 1:11 下午
 */
public class ThreadId {

    static final AtomicLong
            nextId=new AtomicLong(0);
    //定义ThreadLocal变量
    static final ThreadLocal<Long>
            tl=ThreadLocal.withInitial(
            ()->nextId.getAndIncrement());
    //此方法会为每个线程分配一个唯一的Id
    static long get(){
        return tl.get();
    }


    public static void main(String[] args) {
        ThreadId threadId = new ThreadId();
        for (int i = 0; i < 10; i++) {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(ThreadId.get());
                }
            });
            th.start();
        }
    }

}
