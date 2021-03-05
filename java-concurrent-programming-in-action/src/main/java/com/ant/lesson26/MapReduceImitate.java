package com.ant.lesson26;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * <p>
 * 模拟 MapReduce 统计一个文件里的每个单词的数量
 * </p>
 *
 * @author Ant
 * @since 2021/2/24 6:06 下午
 */
public class MapReduceImitate {

    public static void main(String[] args) {
        String[] fc = {
                "hello world",
                "hello me",
                "hello fork",
                "hello join",
                "fork join in world"
        };

        // 创建 ForkJoin 线程池
        ForkJoinPool fjp = new ForkJoinPool(3);

        // 创建任务
        MR mr = new MR(fc, 0, fc.length);

        // 启动任务
        Map<String, Long> result = fjp.invoke(mr);

        // 输出结果
        result.forEach((k, v)-> System.out.println(k + ":" + v));

    }

    static class MR extends RecursiveTask<Map<String, Long>> {

        private String[] fc;

        private int start, end;

        public MR(String[] fc, int fr, int to) {
            this.fc = fc;
            this.start = fr;
            this.end = to;
        }

        @Override
        protected Map<String, Long> compute() {
            System.out.println("当前线程-->" + Thread.currentThread().getName());
            if (end - start == 1) {
                return calc(fc[start]);
            } else {
                int mid = (start + end) / 2;
                MR mr1 = new MR(fc, start, mid);
                mr1.fork();

                MR mr2 = new MR(fc, mid, end);

                // 计算子任务，并返回合并的结果
                return merge(mr2.compute(), mr1.join());
            }
        }

        /**
         * <p>
         * 合并结果
         * </p>
         * 
          * @param 
         * @return {@link Map<String, Long>}
         */
        private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long>  r2) {
            Map<String, Long> result = new HashMap<>();
            result.putAll(r1);
            // 结果合并
            r2.forEach((k, v) -> {
                Long c = result.get(k);
                if (c != null) {
                    result.put(k, c + v);
                } else {
                    result.put(k, v);
                }
            });
            return result;
        }

        /**
         * <p>
         * 统计单词数量
         * </p>
         * 
          * @param line
         * @return {@link Map<String, Long>}
         */
        private Map<String, Long> calc(String line) {
            Map<String, Long> result = new HashMap<>();

            // 分割单词
            String[] words = line.split("\\s+");
            // 统计单词数量
            for (String w : words) {
                Long v = result.get(w);
                if (v != null) {
                    result.put(w, v+1);
                } else {
                    result.put(w, 1L);
                }
            }

            return result;
        }

    }



}
