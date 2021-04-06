package com.ant.lesson32;

import com.ant.lesson29.Router;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 路由表信息
 * </p>
 *
 * @author Ant
 * @since 2021/3/30 4:28 下午
 */
public class RouterTable {
    // Map<接口名, 路由集合>
    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt
            = new ConcurrentHashMap<>();

    // 路由表是否发生变化
    volatile boolean changed;

    ScheduledExecutorService ses
            = Executors.newSingleThreadScheduledExecutor();

    // 启动定时任务
    // 将变更后的路由表写入本地文件
    public void startLocalSaver() {
        ses.scheduleWithFixedDelay(() -> {

        }, 1, 1, TimeUnit.SECONDS);
    }

    // 保存路由表到本地文件
    void autoSave() {
        if (!changed) {
            return;
        }
        changed = false;
        // 将路由表写入本地文件
        // 省略其方法实现
        this.save2Local();
    }

    public void remove(Router router) {
        CopyOnWriteArraySet<Router> routers = rt.get(router.getIface());
        if (null != routers) {
            // 路由表已经发生变化
            changed = true;
        }
    }

    // 增加路由
    public void add(Router router) {
        CopyOnWriteArraySet<Router> routers = rt.computeIfAbsent(router.getIface(), r ->
                new CopyOnWriteArraySet<>());
        routers.add(router);

        // 路由表已发生变化
        changed = true;
    }

    private void save2Local() {
        //.....
    }


}
