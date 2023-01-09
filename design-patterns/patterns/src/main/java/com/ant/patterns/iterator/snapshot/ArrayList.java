package com.ant.patterns.iterator.snapshot;


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
        return new SnapshotArrayIterator(this);
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
        elementData[size] = e;
        size++;
        return true;
    }

    @Override
    public E get(int cursor) {
        E elementDatum = (E) elementData[cursor];
        return elementDatum;
    }

    @Override
    public void remove(E e) {
        for (int i = 0; i < elementData.length; i++) {
            if(elementData[i].equals(e)) {
                for (int j = i; j < elementData.length-1; j++) {
                    elementData[j] = elementData[j+1];
                }
                break;
            }
        }

    }

    public void addAll(ArrayList<E> list) {
        if (elementData == null) {
            elementData = new Object[DEFAULT_SIZE];
        }
        for (int i = 0; i < list.elementData.length; i++) {
            this.elementData[i] = list.elementData[i];
        }
    }

}
