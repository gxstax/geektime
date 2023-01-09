package com.ant.patterns.iterator.base;

import java.util.NoSuchElementException;

/**
 * <p>
 * 迭代器实现类
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:36 上午
 */
public class ArrayIterator<E> implements Iterator<E> {

    /**
     * 游标
     */
    private int cursor;

    private ArrayList<E> arrayList;

    public ArrayIterator(ArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        return cursor != arrayList.size() ;
    }

    @Override
    public void next() {
        cursor++;
    }

    @Override
    public E currentItem() {
        if (cursor >= arrayList.size()) {
            throw new NoSuchElementException();
        }
        return arrayList.get(cursor);
    }
}
