package com.ant.patterns.iterator.base;

import com.ant.patterns.iterator.base.ArrayIterator;
import com.ant.patterns.iterator.base.ArrayList;
import com.ant.patterns.iterator.base.Iterator;

/**
 * <p>
 * 测试demo
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:54 上午
 */
public class Demo {

    public static void main(String[] args) {
        ArrayList names = new ArrayList<>();
        names.add("Ant");
        names.add("马以");
        names.add("mayi");
        Iterator iterator = new ArrayIterator(names);
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }
}
