package com.ant.lesson19;


import io.netty.util.concurrent.DefaultThreadFactory;
import scala.concurrent.java8.FuturesConvertersImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * <p>
 * CountDownLath 示例
 * </P>
 *
 * @author Ant
 * @since 2022/12/28 16:48
 **/
public class CountDownLathDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLathDemo demo = new CountDownLathDemo();
        demo.sync();
    }

    /**
     * <p>
     * 实现1
     * </p>
     *
     * @param
     * @return void
     */
    public void sync() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        int loop = 10;
        while (loop > 0) {
            CountDownLatch countDownLath = new CountDownLatch(2);

            List<String> pos = new ArrayList<>();
            List<String> dos = new ArrayList<>();

            threadPoolExecutor.execute(()-> {
                // 查询未对账订单
                getPOrders(pos);

                countDownLath.countDown();
            });

            threadPoolExecutor.execute(()-> {
                // 查询派送单
                getDOrders(dos);

                countDownLath.countDown();
            });

            // 等待两个查询操作结束
            countDownLath.await();

            // 执行对账操作
            List diff = check(pos, dos);
            // 差异写入差异库
            boolean save = save(diff);

            System.out.println(save);
            --loop;
        }
    }


    /**
     * <p>
     * 查询未对账单
     * </p>
     *
     * @param
     * @return int[]
     */
    public void getPOrders(List pos) {
        System.out.println("查询未对账单...");
    }

    /**
     * <p>
     * 查询派送单
     * </p>
     *
     * @param
     * @return int[]
     */
    public void getDOrders(List dos) {
        System.out.println("查询派送单...");
    }

    /**
     * <p>
     * 对账
     * </p>
     *
     * @param
     * @return void
     */
    private List check(List pos, List dos) {
        System.out.println("对账...");
        return new ArrayList();
    }

    /**
     * <p>
     * 差异入库
     * </p>
     *
     * @param diff
     * @return boolean
     */
    public boolean save(List diff) {
        System.out.println("差异入库...");
        return true;
    }
}
