package com.ant.lesson19;


import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * <p>
 * CyclicBarrier 示例
 * </P>
 *
 * @author Ant
 * @since 2022/12/28 16:48
 **/
public class CyclicBarrierDemo {

    // 订单队列
    Vector<String> pos;
    // 派送单队列
    Vector<String> dos;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1),
            new DefaultThreadFactory("sync", true),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        executor.execute(()-> callBackCheck(pos, dos));
    });

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrierDemo demo = new CyclicBarrierDemo();
        demo.pos = new Vector<>();
        demo.dos = new Vector<>();
        demo.checkAll();
    }

    /**
     * <p>
     * 实现2
     * </p>
     *
     * @param
     * @return void
     */
    public void checkAll() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                pos.add(getPOrders(String.valueOf(i)));
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                dos.add(getDOrders(String.valueOf(i)));
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void callBackCheck(Vector<String> pos, Vector<String> dos) {
        System.out.println("检查");
        String p = pos.remove(0);
        String d = dos.remove(0);

        String diff = check(p, d);

        boolean save = save(diff);
    }



    /**
     * <p>
     * 查询未对账单
     * </p>
     *
     * @param
     * @return int[]
     */
    public String getPOrders(String i) {
        System.out.println("查询未对账单...");
        return i;
    }

    /**
     * <p>
     * 查询派送单
     * </p>
     *
     * @param
     * @return int[]
     */
    public String getDOrders(String d) {
        System.out.println("查询派送单...");
        return d;
    }

    /**
     * <p>
     * 对账
     * </p>
     *
     * @param
     * @return void
     */
    private String check(String pos, String dos) {
        System.out.println("对账...");
        return "diff";
    }

    /**
     * <p>
     * 差异入库
     * </p>
     *
     * @param diff
     * @return boolean
     */
    public boolean save(String diff) {
        System.out.println("差异入库...");
        return true;
    }
}
