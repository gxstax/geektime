package com.ant.patterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 单例（静态内部类）
 * 优点：既保证了线程安全，又能做到延迟加载
 *
 * 大佬观点:
 * SingletonHolder 是一个静态内部类，当外部类 IdGenerator 被加载的时候，
 * 并不会创建 SingletonHolder 实例对象。只有当调用 getInstance() 方法时，
 * SingletonHolder 才会被加载，这个时候才会创建 instance。instance 的唯一性、创建过程的线程安全性，
 * 都由 JVM 来保证。所以，这种实现方法既保证了线程安全，又能做到延迟加载。
 *
 *
 * JVM 规范枚举了下述多种类的初始化触发情况：
 * 1. 当虚拟机启动时，初始化用户指定的主类；
 * 2. 当遇到用以新建目标类实例的 new 指令时，初始化 new 指令的目标类；
 * 3. 当遇到调用静态方法的指令时，初始化该静态方法所在的类；
 * 4. 当遇到访问静态字段的指令时，初始化该静态字段所在的类；
 * 5. 子类的初始化会触发父类的初始化；
 * 6. 如果一个接口定义了 default 方法，那么直接实现或者间接实现该接口的类的初始化，会触发该接口的初始化；
 * 7. 使用反射 API 对某个类进行反射调用时，初始化这个类；
 * 8. 当初次调用 MethodHandle 实例时，初始化该 MethodHandle 指向的方法所在的类。
 * </p>
 *
 * @author Ant
 * @since 2020/02/09 7:46 下午
 */
public class IdGeneratorByStaticInnerClass {
    private AtomicLong id = new AtomicLong(0);

    private static IdGeneratorByStaticInnerClass instance;
    /**
     * 构造函数私有，避免外部实例化
     */
    private IdGeneratorByStaticInnerClass(){}

    /**
     * 当需要的事后调用的时候才会被创建
     */
    private static class LazyHolder {
        // 类的初始化过程伴随着常量的初始化，从而伴随着 IdGeneratorByStaticInnerClass 类的初始化
        // 而类的初始化是由 JVM 加锁实现，保证了单例
        private static IdGeneratorByStaticInnerClass INSTANCE = new IdGeneratorByStaticInnerClass();
    }

    public static IdGeneratorByStaticInnerClass getInstance() {
        /**
         * 只有当调用 IdGeneratorByStaticInnerClass.getInstance() 程序才会访问 LazyHolder.INSTANCE；
         * 访问 LazyHolder.INSTANCE; 就会伴随着初始化所在的类-LazyHolder（对应第4种情况);
         * LazyHolder 类初始化过程必然会伴随着常量-INSTANCE 的初始化，
         * 这部分由Java 虚拟机会通过加锁来确保类的方法仅被执行一次，继而新建了一个 IdGeneratorByStaticInnerClass 的实例；
         */
        return LazyHolder.INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }

}
