package com.ant.exceptios;


/**
 * <p>
 * TODO
 * </P>
 *
 * @author Ant
 * @since 2022/10/26 9:31 下午
 **/
public class Foo {

    private int tryBlock;
    private int catchBlock;
    private int finallyBlock;
    private int methodExit;

    public void test() {
        try {
            tryBlock = 0;
        } catch (Exception e) {
            catchBlock = 1;
        } finally {
            finallyBlock = 2;
        }
        methodExit = 3;
    }
}
