package com.ant.patterns.observer.my_event_bus;

import java.util.concurrent.Executor;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/3/25 7:02 下午
 */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
