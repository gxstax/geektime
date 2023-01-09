package com.ant.patterns.iterator.base;

/**
 * <p>
 * 迭代器抽象
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:29 上午
 */
public interface Iterator<E> {

    /**
     * 判断是否有下一个元素
     */
    boolean hasNext();

    /**
     * 移动游标
     */
    void next();

    /**
     * 获取当前元素
     */
    E currentItem();
}
