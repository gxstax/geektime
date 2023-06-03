package com.ant.patterns.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * <p>
 * 剖析 Servlet Filter 实现方式 {@link Filter}
 *
 * 如果我们要添加一个过滤器，
 * 只需要写一个实现javax.servlet.Filter接口的类
 * 添加过滤器非常方便，不需要修改任何代码，定义一个实现 javax.servlet.Filter 的类，
 * 再改改配置就搞定了，完全符合开闭原则。那 Servlet Filter 是如何做到如此好的扩展性的呢？
 * </p>
 *
 * @author GaoXin
 * @since 2020/3/28 10:41 下午
 */
public class LogFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(LogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 在创建Filter时自动调用，
        // 其中filterConfig包含这个Filter的配置参数，比如name之类的（从配置文件中读取的）
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        log.info("拦截客户端的请求.");
        chain.doFilter(servletRequest, servletResponse);
        log.info("拦截发送给客户端的响应.");
    }

    @Override
    public void destroy() {
        // 在销毁filter是自动调用
    }
}
