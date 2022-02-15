package com.ant.lesson09;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/3/13 5:37 下午
 */
public class ThinkingInJava {

    public static void main(String[] args) {

    }

    /**
     * <p>
     * 这看上去一点问题没有，实际上却是几乎起不了作用。
     * 原因是这段代码在执行的时候，大部分时间都是阻塞在 sleep(100) 上，
     * 当其他线程通过调用th.interrupt().来中断 th 线程时，大概率地会触发 InterruptedException 异常，
     * 在触发 InterruptedException 异常的同时，JVM 会同时把线程的中断标志位清除，所以这个时候th.isInterrupted()返回的是 false。
     * </p>
     *
     * @param
     * @return void
     */
    public void interrupted1() {
        Thread th = Thread.currentThread();
        while(true) {
            if(th.isInterrupted()) {
                break;
            }
            // 省略业务代码无数
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>
     * 正确的处理方式应该是捕获异常之后重新设置中断标志位
     * </p>
     *
     * @param
     * @return void
     */
    public void interrupted2() {
        Thread th = Thread.currentThread();
        while(true) {
            if(th.isInterrupted()) {
                break;
            }
            // 省略业务代码无数
            try {
                Thread.sleep(100);
            }catch (InterruptedException e) {
                // 正确的处理方式应该是捕获异常之后重新设置中断标志位
                th.interrupt();
                e.printStackTrace();
            }
        }
    }

}
