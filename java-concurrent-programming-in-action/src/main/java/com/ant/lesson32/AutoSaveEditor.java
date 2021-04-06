package com.ant.lesson32;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * AutoSaveEditor 示例
 * </p>
 *
 * @author Ant
 * @since 2021/3/27 2:17 下午
 */
public class AutoSaveEditor {
    // 文件是否被修改过
    boolean changed  = false;

    // 定时任务线程池
    ScheduledExecutorService ses =
            Executors.newSingleThreadScheduledExecutor();

    // 定时任务自动保存
    void startAutoSave() {
        ses.scheduleWithFixedDelay(() -> {
            autoSave();
        }, 5, 5, TimeUnit.SECONDS);
    }

    /**
     * <p>
     * 自动存盘
     * </p>
     * 
      * @param 
     * @return void
     */
    void autoSave() {
        synchronized (this) {
            if (!changed) {
                return;
            }
        }

        changed = false;

        // 执行存盘操作
        this.execSave();
    }
    
    /**
     * <p>
     * 编辑操作
     * </p>
     * 
      * @param 
     * @return void
     */
    void edit() {
        synchronized (this) {
            changed = true;
        }
    }
    
    /**
     * <p>
     * 执行存盘操作
     * </p>
     * 
      * @param 
     * @return void
     */
    private void execSave() {
        // ...
    }

}
