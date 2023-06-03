package com.ant.patterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 单例（饿汉模式）
 * 缺点：不支持延时加载
 *
 * 大佬观点:
 * 有人觉得这种实现方式不好，因为不支持延迟加载，如果实例占用资源多（比如占用内存多）或初始化耗时长（比如需要加载各种配置文件），
 * 提前初始化实例是一种浪费资源的行为。最好的方法应该在用到的时候再去初始化。
 * 不过，我个人并不认同这样的观点。如果初始化耗时长，那我们最好不要等到真正要用它的时候，才去执行这个耗时长的初始化过程，
 * 这会影响到系统的性能（比如，在响应客户端接口请求的时候，做这个初始化操作，会导致此请求的响应时间变长，甚至超时）。
 * 采用饿汉式实现方式，将耗时的初始化操作，提前到程序启动的时候完成，这样就能避免在程序运行的时候，再去初始化导致的性能问题。
 * 如果实例占用资源多，按照 fail-fast 的设计原则（有问题及早暴露），那我们也希望在程序启动时就将这个实例初始化好。
 * 如果资源不够，就会在程序启动的时候触发报错（比如 Java 中的 PermGen Space OOM），我们可以立即去修复。
 * 这样也能避免在程序运行一段时间后，突然因为初始化这个实例占用资源过多，导致系统崩溃，影响系统的可用性。
 * </p>
 *
 * @author Ant
 * @since 2020/02/09 7:46 下午
 */
public class IdGeneratorByHungery {
    private AtomicLong id = new AtomicLong();

    /**
     * 饿汉模式（类加载的时候初始化）
     */
    private static final IdGeneratorByHungery instance = new IdGeneratorByHungery();
    /**
     * 构造函数私有，避免外部实例化
     */
    private IdGeneratorByHungery(){}

    public static IdGeneratorByHungery getInstance() {
        return instance;
    }

    public long getIdGenerator() {
        return id.incrementAndGet();
    }

}
