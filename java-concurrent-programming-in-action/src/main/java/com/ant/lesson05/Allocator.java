package com.ant.lesson05;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源分配器（需要是单例的）
 * </p>
 *
 * @author Ant
 * @since 2021/3/8 12:31 下午
 */
public class Allocator {

    // 已经被使用的资源列表
    private List<Object> als = new ArrayList<>();
    
    /**
     * <p>
     * 申请资源()
     * </p>
     * 
      * @param 
     * @return boolean
     */
    synchronized boolean apply(Object from, Object to) {
        // 只要需要的资源有一个被使用，那么申请资源就申请不了
        if (als.contains(from) || als.contains(to)) {
            return false;
        }
        als.add(from);
        als.add(to);
        return true;
    }

    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }

}
