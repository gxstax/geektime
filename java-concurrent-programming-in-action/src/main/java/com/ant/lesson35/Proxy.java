package com.ant.lesson35;

/**
 * <p>
 * 监控代理DEMO
 * </p>
 *
 * @author Ant
 * @since 2021/4/6 9:26 上午
 */
public class Proxy {
    // 线程终止标记位
    volatile boolean terminated = false;
    boolean started = false;

    // 采集线程
    Thread rptThread;

    /**
     * <p>
     * 启动采集功能
     * </p>
     * 
      * @param 
     * @return void
     */
    synchronized void start() {
        // 不允许多个线程同时采集
        if (started) {
            return;
        }

        started = true;
        terminated = false;
        rptThread = new Thread(() -> {
            while (!terminated) {
                // 省略采集，回传实现
                report();

                // 每隔两秒钟采集、回传一次
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // 重新设置线程的中断状态
                    Thread.currentThread().interrupt();
                }
            }
            started = false;
        });
        rptThread.start();
    }
    
    /**
     * <p>
     * 终止采集功能
     * </p>
     * 
      * @param 
     * @return void
     */
    public void stop() {
        // 设置中断标志位
        terminated = true;
        // 中断线程 rptThread
        rptThread.interrupt();
    }

    private void report() {

    }
}
