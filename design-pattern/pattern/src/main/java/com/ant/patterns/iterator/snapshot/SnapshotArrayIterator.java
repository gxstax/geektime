package com.ant.patterns.iterator.snapshot;


/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2020/4/6 10:13 下午
 */
public class SnapshotArrayIterator<E> implements Iterator<E> {
    /**
     * 游标
     */
    private int cursor;

    /**
     * 迭代对象
     */
    private ArrayList<E> arrayList;

    private ArrayList<E> snapArrayList = new ArrayList<>();

    public SnapshotArrayIterator(ArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
        this.snapArrayList.addAll(arrayList);
    }

    @Override
    public boolean hasNext() {
        return this.cursor != snapArrayList.size();
    }

    @Override
    public E next() {
        cursor++;
        return snapArrayList.get(cursor);
    }

}
