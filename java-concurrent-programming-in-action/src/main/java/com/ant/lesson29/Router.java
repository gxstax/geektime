package com.ant.lesson29;

import lombok.Getter;

import java.util.Objects;

/**
 * <p>
 * 路由表
 * </p>
 *
 * @author Ant
 * @since 2021/3/24 6:38 下午
 */
@Getter
public class Router {

    private final String ip;
    private final String port;
    private final String iface;


    public Router(String ip, String port, String iface) {
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Router router = (Router) o;
        return Objects.equals(ip, router.ip) &&
                Objects.equals(port, router.port) &&
                Objects.equals(iface, router.iface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port, iface);
    }
}
