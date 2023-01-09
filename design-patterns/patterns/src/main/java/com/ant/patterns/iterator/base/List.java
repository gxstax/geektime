package com.ant.patterns.iterator.base;

import com.ant.patterns.iterator.base.Iterator;

/**
 * <p>
 * 容器抽象
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:33 上午
 */
public interface List<E> {

    /**
     * 组合迭代
     */
    Iterator iterator();

    /**
     * 容器大小
     */
    int size();

    /**
     * 新增元素
     */
    boolean add(E e);

    /**
     * 获取元素信息
     */
    E get(int cursor);

}
