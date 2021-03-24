package com.ant.lesson24;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * CompletableFuture 示例
 * </p>
 *
 * @author Ant
 * @since 2021/3/22 9:14 上午
 */
public class CompletableFutureDemo2 {

    public static void main(String[] args) {
        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                        () -> "Hello World")      //①
                        .thenApply(s -> s + " QQ")  //②
                        .thenApply(String::toUpperCase);//③

        System.out.println(f0.join());

        // 输出结果
        // HELLO WORLD QQ




        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    int t = getRandom(5, 10);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    int t = getRandom(5, 10);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2,s -> s);

        System.out.println(f3.join());
    }

    public static int getRandom(int origin, int bound) {
        Random random = new Random(origin);
        return random.nextInt(bound);
    }

    public static void sleep(int t, TimeUnit unit) {
        try {
            unit.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
