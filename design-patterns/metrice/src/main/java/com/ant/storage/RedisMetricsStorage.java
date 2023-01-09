package com.ant.storage;

import com.ant.bean.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Redis数据收集
 * </p>
 *
 * @author Ant
 * @since 2020/3/9 9:13 上午
 */
public class RedisMetricsStorage implements MetricsStorage{

    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        System.out.println("保存请求数据.....");
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeStamp, long endTimeStamp) {
        System.out.println("获取指定ApiName单位时间内请求信息");
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeStamp, long endTimeStamp) {
        System.out.println("获取指定时间内请求信息");
        return null;
    }
}
