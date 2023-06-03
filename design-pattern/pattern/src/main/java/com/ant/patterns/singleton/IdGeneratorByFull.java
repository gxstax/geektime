package com.ant.patterns.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 单例（饱汉模式）
 * 缺点：加锁导致函数并法度低（串行操作，并发度为 1 ）
 *
 * 大佬观点:
 * 而这个函数是在单例使用期间，一直会被调用。如果这个单例类偶尔会被用到，那这种实现方式还可以接受。
 * 但是，如果频繁地用到，那频繁加锁、释放锁及并发度低等问题，会导致性能瓶颈，这种实现方式就不可取了。
 * </p>
 *
 * @author Ant
 * @since 2020/02/09 7:46 下午
 */
public class IdGeneratorByFull {
    private AtomicLong id = new AtomicLong(0);

    private static IdGeneratorByFull instance;
    /**
     * 构造函数私有，避免外部实例化
     */
    private IdGeneratorByFull(){}

    public static synchronized IdGeneratorByFull getInstance() {
        if (null == instance) {
            instance = new IdGeneratorByFull();
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }

}
