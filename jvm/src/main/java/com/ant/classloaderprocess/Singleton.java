package com.ant.classloaderprocess;


/**
 * <p>
 * JVM 规范枚举了下述多种类的初始化触发情况：
 * 1. 当虚拟机启动时，初始化用户指定的主类；
 * 2. 当遇到用以新建目标类实例的 new 指令时，初始化 new 指令的目标类；
 * 3. 当遇到调用静态方法的指令时，初始化该静态方法所在的类；
 * 4. 当遇到访问静态字段的指令时，初始化该静态字段所在的类；
 * 5. 子类的初始化会触发父类的初始化；
 * 6. 如果一个接口定义了 default 方法，那么直接实现或者间接实现该接口的类的初始化，会触发该接口的初始化；
 * 7. 使用反射 API 对某个类进行反射调用时，初始化这个类；
 * 8. 当初次调用 MethodHandle 实例时，初始化该 MethodHandle 指向的方法所在的类。
 * </P>
 *
 * @author Ant
 * @since 2023/01/09 22:26
 **/
public class Singleton {

    /**
     * 屏蔽外部实例化
     */
    private Singleton() {}

    private static class LazyHolder {
        // 类的初始化过程伴随着常量的初始化，从而伴随着 Singleton 类的初始化
        // 而类的初始化是由 JVM 加锁实现，保证了单例
        final static Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        /**
         * 只有当调用 Singleton.getInstance() 程序才会访问 LazyHolder.INSTANCE；
         * 访问 LazyHolder.INSTANCE; 就会伴随着初始化所在的类-LazyHolder（对应第4种情况);
         * LazyHolder 类初始化过程必然会伴随着常量-INSTANCE 的初始化，
         * 这部分由Java 虚拟机会通过加锁来确保类的方法仅被执行一次，继而新建了一个 Singleton 的实例；
         */
        Singleton INSTANCE = Singleton.getInstance();
    }

}
