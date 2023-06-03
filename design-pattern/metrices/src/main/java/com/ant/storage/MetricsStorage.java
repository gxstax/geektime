package com.ant.storage;

import com.ant.bean.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 原始数据收集
 * </p>
 *
 * @author Ant
 * @since 2020/3/9 9:10 上午
 */
public interface MetricsStorage {

    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeStamp, long endTimeStamp);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeStamp, long endTimeStamp);
}
