package com.ant.exceptios;


/**
 * <p>
 * TODO
 * </P>
 *
 * @author Ant
 * @since 2022/10/26 9:15 下午
 **/
public class MayThrowException {
    public static void main(String[] args) {
        try {
            mayThrowException();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }

    public static void mayThrowException() {
        System.out.println(1/0);
    }
}
