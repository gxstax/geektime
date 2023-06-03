package com.ant.patterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 单例（双重检测）
 * 优点：既支持延迟加载、又支持高并发的单例实现方式，也就是双重检测实现方式。
 * 缺点：低版本JDK中因为指令重排序，可能会导致 IdGenerator 对象被 new 出来，
 *      并且赋值给 instance 之后，还没来得及初始化
 *
 * 大佬观点:
 * 网上有人说，这种实现方式有些问题。因为指令重排序，可能会导致 IdGenerator 对象被 new 出来，
 * 并且赋值给 instance 之后，还没来得及初始化（执行构造函数中的代码逻辑），就被另一个线程使用了。
 * 要解决这个问题，我们需要给 instance 成员变量加上 volatile 关键字，禁止指令重排序才行。
 * 实际上，只有很低版本的 Java 才会有这个问题。
 * 我们现在用的高版本的 Java 已经在 JDK 内部实现中解决了这个问题（解决的方法很简单，
 * 只要把对象 new 操作和初始化操作设计为原子操作，就自然能禁止重排序）。
 * </p>
 *
 * @author Ant
 * @since 2020/02/09 7:46 下午
 */
public class IdGeneratorByDoubleCheck {
    private AtomicLong id = new AtomicLong(0);

    private static IdGeneratorByDoubleCheck instance;
    /**
     * 构造函数私有，避免外部实例化
     */
    private IdGeneratorByDoubleCheck(){}

    public static synchronized IdGeneratorByDoubleCheck getInstance() {
        if (null == instance) {
            // 添加类级别的锁
            synchronized (IdGeneratorByDoubleCheck.class) {
                if (null == instance) {
                    instance = new IdGeneratorByDoubleCheck();
                }
            }
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }

}
