package com.ant.lesson27;

import java.util.function.IntBinaryOperator;

/**
 * <p>
 * {@link Adapt} 注解测试类
 * </P>
 *
 * @author Ant
 * @since 2023/01/12 12:33
 **/
public class Bar {

    @Adapt(IntBinaryOperator.class)
    public static int add(int a, int b) {
        return a + b;
    }

}
