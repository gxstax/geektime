package com.ant.dynamic_proxy;

import com.ant.bean.RequestInfo;
import com.ant.collector.MetricsCollector;
import com.ant.storage.MetricsStorage;
import com.ant.storage.RedisMetricsStorage;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2020/3/20 8:34 上午
 */
public class MetricsCollectorProxy {

    private MetricsCollector metricsCollector;

    public MetricsCollectorProxy() {
        MetricsStorage metricsStorage = new RedisMetricsStorage();
        this.metricsCollector = new MetricsCollector(metricsStorage);
    }

    public Object createProxy(Object proxiedObject) {
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject);
        Object obj = Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, handler);
        return obj;
    }

    public class DynamicProxyHandler implements InvocationHandler {

        private Object proxiedObject;


        public DynamicProxyHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTimestamp = System.currentTimeMillis();
            Object result = method.invoke(proxiedObject, args);
            long endTimeStamp = System.currentTimeMillis();
            long responseTime = endTimeStamp - startTimestamp;
            String apiName = proxiedObject.getClass().getName() + ":" + method.getName();
            RequestInfo requestInfo = new RequestInfo(apiName, responseTime, startTimestamp);
            metricsCollector.recordRequest(requestInfo);
            return result;
        }
    }

    public static void main(String[] args) {
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        ICollertorController proxy1 = (ICollertorController) proxy.createProxy(new CollertorController());
        proxy1.collector();
    }
}
