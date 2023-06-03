package com.ant.collector;

import com.ant.bean.RequestInfo;
import com.ant.storage.MetricsStorage;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 收集器
 * </p>
 *
 * @author Ant
 * @since 2020/3/9 9:10 上午
 */
public class MetricsCollector {
    private MetricsStorage metricsStorage;

    // 依赖注入
    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    // 用一个函数替代最小原型中的两个函数
    public void recordRequest(RequestInfo requestInfo) {
        if (null == requestInfo || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }

}
