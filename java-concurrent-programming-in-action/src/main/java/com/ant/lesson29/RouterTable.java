package com.ant.lesson29;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * 路由表信息
 * </p>
 *
 * @author Ant
 * @since 2021/3/25 9:57 上午
 */
public class RouterTable {
    // Key: 接口名
    // Value: 路由集合
    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt
            = new ConcurrentHashMap<>();

    // 根据接口名称获取路由表
    public Set<Router> get(String iFace) {
        return rt.get(iFace);
    }

    // 删除路由
    public void remove(Router router) {
        Set<Router> routers = rt.get(router.getIface());
        if (routers != null) {
            routers.remove(router);
        }
    }

    // 增加路由
    public void add(Router router) {
        Set<Router> routers = rt.computeIfAbsent(router.getIface(),
                r -> new CopyOnWriteArraySet<>());
        routers.add(router);
    }

}
