package com.ant;

import com.ant.bean.RequestInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 数据计算统计
 * </p>
 *
 * @author Ant
 * @since 2020/3/9 9:11 上午
 */
public class Aggreator {

    public static RequestStat aggreator(List<RequestInfo> requestInfos, long durationInMillis) {
        double maxResponseTime = Double.MAX_VALUE;
        double minResponseTime = Double.MIN_VALUE;
        double avgResponseTime = -1;
        double p999ResponseTime = -1;
        double p99ResponseTime = -1;
        double sumResponseTime = 0;
        long count = 0;

        for (RequestInfo requestInfo : requestInfos) {
            double responseTime = requestInfo.getResponseTime();
            if (responseTime > maxResponseTime) {
                maxResponseTime = responseTime;
            }
            if (responseTime < minResponseTime) {
                minResponseTime = responseTime;
            }
            sumResponseTime += responseTime;
            count++;
        }
        avgResponseTime = sumResponseTime / count;
        long tps = (long)(count / durationInMillis * 1000);
        Collections.sort(requestInfos, new Comparator<RequestInfo>() {
            @Override
            public int compare(RequestInfo o1, RequestInfo o2) {
                double diff = o1.getResponseTime() - o2.getResponseTime();
                if (diff < 0.0) {
                    return -1;
                } else if (diff > 0.0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        int p99idx = (int) (count * 0.99);
        int p999idx = (int) (count * 0.999);
        p99ResponseTime = requestInfos.get(p99idx).getResponseTime();
        p999ResponseTime = requestInfos.get(p999idx).getResponseTime();

        // 构建返回结果
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(maxResponseTime);
        requestStat.setMinResponseTime(minResponseTime);
        requestStat.setAvgResponseTime(avgResponseTime);
        requestStat.setP99ResponseTime(p99ResponseTime);
        requestStat.setP999ResponseTime(p999ResponseTime);
        requestStat.setCount(count);
        requestStat.setTps(tps);

        return requestStat;
    }
}
