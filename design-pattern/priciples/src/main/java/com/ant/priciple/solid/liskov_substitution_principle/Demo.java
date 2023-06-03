package com.ant.priciple.solid.liskov_substitution_principle;

import com.sun.tools.internal.ws.processor.model.Response;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/3/2 9:11 上午
 */
public class Demo {
    public void demoFunction(Transporter transporter) {
        Request request = new Request();
        //...省略设置request中数据值的代码...
        Response response = transporter.sendRequest(request);
        //...省略其他逻辑...
    }

    public static void main(String[] args) {
        // 里式替换原则
        Demo demo = new Demo();
        demo.demoFunction(new SecurityTransporter(null, "", ""/*省略参数*/));
    }
}
