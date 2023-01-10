package com.ant.lesson14;


/**
 * <p>
 * TODO
 * </P>
 *
 * @author Ant
 * @since 2023/01/10 14:31
 **/
public class SynchronizedDemo {
    public synchronized void foo(Object lock) {
        lock.hashCode();
    }
}
