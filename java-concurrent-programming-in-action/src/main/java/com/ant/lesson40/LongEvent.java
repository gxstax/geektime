package com.ant.lesson40;

/**
 * <p>
 * 自定义 Event
 * </p>
 *
 * @author Ant
 * @since 2021/4/9 9:59 上午
 */
public class LongEvent {
    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
