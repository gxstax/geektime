package com.ant.reflect;


import java.lang.reflect.Method;

/**
 * <p>
 * V3版本
 * </P>
 *
 * @author Ant
 * @since 2022/10/27 10:25 下午
 **/
public class TestV3 {

    public static void target(int i) {
        // 空方法
    }

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("com.ant.reflect.TestV3");
        Method method = klass.getMethod("target", int.class);

        Object[] arg = new Object[1]; // 在循环外构造参数数组
        arg[0] = 128;

        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }

            method.invoke(null, arg);
        }
    }
}


