package com.ant.patterns.iterator.base;

/**
 * <p>
 * 容器实现类
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:34 上午
 */
public class ArrayList<E> implements List<E> {

    private int size;

    private static final Integer DEFAULT_SIZE = 10;

    transient Object[] elementData;

    @Override
    public Iterator iterator() {
        return new ArrayIterator(this);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        // 这里不考虑安全问题，只做模拟而已
        if (elementData == null) {
            elementData = new Object[DEFAULT_SIZE];
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public E get(int cursor) {
        E elementDatum = (E) elementData[cursor];
        return elementDatum;
    }

}
