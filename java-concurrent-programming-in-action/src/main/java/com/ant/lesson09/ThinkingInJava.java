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
        Thread th = Thread.currentThread();
        while(true) {
            if(th.isInterrupted()) {
                break;
            }
            // 省略业务代码无数
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

}
