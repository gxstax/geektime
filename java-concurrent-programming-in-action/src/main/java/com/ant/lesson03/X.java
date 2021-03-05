package com.ant.lesson03;

/**
 * <p>
 * synchronized 关键字
 * </p>
 *
 * @author Ant
 * @since 2021/3/4 12:54 下午
 */
public class X {

    // 修饰非静态方法 （修饰非静态方法锁定的是当前示例对象 this）
    synchronized void foo() {
        // 临界区
    }

    // 修饰静态方法 （锁定当前类的 Class 对象，这里就是 Class X）
    synchronized static void bar() {
        // 临界区
    }

    // 修饰代码块
    Object obj = new Object();
    void baz() {
        synchronized (obj) {
            // 临界区
        }
    }

}
