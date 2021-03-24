package com.ant.lesson28;

/**
 * <p>
 * Immutability 模式示例
 * </p>
 *
 * @author Ant
 * @since 2021/3/24 12:51 下午
 */
public class ImmutabilityDemo {

    static final Long cache[] = new Long[-(-128) + 127 + 1];

    static {
        for(int i = 0; i < cache.length; i++)
            cache[i] = new Long(i - 128);
    }

    public static void main(String[] args) {
        System.out.println(-(-128) + 127 + 1);
    }

}
