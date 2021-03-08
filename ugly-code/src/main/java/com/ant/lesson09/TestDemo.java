package com.ant.lesson09;

/**
 * <p>
 * 测试Demo
 * </p>
 *
 * @author Ant
 * @since 2021/1/24 3:34 下午
 */
public class TestDemo {

    private static void approve(Book book) {
        book.approve();
        System.out.println(book);
    }

    public static void main(String[] args) {
        Book book = new Book();
        approve(book);
    }
}
