package com.ant.lesson02;

/**
 * <p>
 * Volatile 示例
 * </p>
 *
 * @author Ant
 * @since 2021/3/4 9:34 上午
 */
public class VolatileExample {
    int x = 0;
    volatile boolean v = false;

    public void write() {
        if (v == false) {
            x = 42;
            v = true;
        }
    }

    public void reader() {
        if(v == true) {
            System.out.println("x--> " + x);
        }
    }

    public static void main(String[] args) {

        VolatileExample example = new VolatileExample();

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                example.write();
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                example.reader();
            }
        });

        th1.start();
        th2.start();

    }
}
