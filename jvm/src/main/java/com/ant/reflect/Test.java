package com.ant.reflect;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 * TODO
 * </P>
 *
 * @author Ant
 * @since 2022/10/27 10:07 下午
 **/
public class Test {

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.ant.reflect.Test");
        Method method = clazz.getMethod("target", int.class);
        method.invoke(null, 0);
    }
}
