package com.ant.lesson30;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <p>
 * 安全时间格式化
 * </p>
 *
 * @author Ant
 * @since 2021/3/25 1:37 下午
 */
public class SafeDateFormat {
    //定义ThreadLocal变量
    static final ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    );

    static DateFormat get() {
        return tl.get();
    }



    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 不同线程执行下面代码
                    // 返回的 df 是不同的
                    DateFormat df = SafeDateFormat.get();
                    System.out.println(df.hashCode());
                }
            });
            th.start();
        }
    }
}
