package com.ant.patterns.finitestatemachine;

/**
 * <p>
 * 状态枚举
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:31 下午
 */
public enum State {
    /**
     * 正常马里奥
     */
    SMALL(0),

    /**
     * 超级马里奥
     */
    SUPER(1),

    /**
     * 喷火马里奥
     */
    FIRE(2),

    /**
     * 斗篷马里奥
     */
    CAPE(3);

    private int value;

    State(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
