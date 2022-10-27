package com.ant.reflect;


import java.lang.reflect.Method;

/**
 * <p>
 * V2版本
 * </P>
 *
 * @author Ant
 * @since 2022/10/27 10:25 下午
 **/
public class TestV2 {

    public static void target(int i) {
        // 空方法
    }

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("com.ant.reflect.TestV2");
        Method method = klass.getMethod("target", int.class);

        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }

            method.invoke(null, 128);
        }
    }
}

