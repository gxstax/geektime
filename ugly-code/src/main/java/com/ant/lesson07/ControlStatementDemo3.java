package com.ant.lesson07;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 滥用控制语句示例 3
 * </p>
 *
 * @author Ant
 * @since 2021/1/14 5:56 下午
 */
public class ControlStatementDemo3 {

    private static final Integer START_CHARGING_SEQUENCE = 10;
    private static final Integer FURTHER_CHARGING_SEQUENCE = 10;

    private Integer sequenceNumber;

    private UserLevelService userLevelService;

    private static Map<UserLevel, UserLevelService> userLevelServiceMap = new HashMap();

    static {
        userLevelServiceMap.put(UserLevel.SILVER, new SilverUserLevel());
        userLevelServiceMap.put(UserLevel.GOLD, new GoldUserLevel());
        userLevelServiceMap.put(UserLevel.PLATINUM, new PlatinumUserLevel());
    }

    public double getBookPrice(final User user, final Book book) {
        return userLevelServiceMap.get(user.getLevel()).getBookPrice(book);
    }

    public double getEpubPrice(final User user, final Epub epub) {
        return userLevelServiceMap.get(user.getLevel()).getEpubPrice(epub);
    }

    public static UserLevelService getUserLevelService(final User user) {
        return userLevelServiceMap.get(user.getLevel());
    }

    public static void main(String[] args) {
        ControlStatementDemo3 controlStatementDemo3 = new ControlStatementDemo3();
        Book book = new Book();
        book.setPrice(100.00);
        System.out.println(controlStatementDemo3.getBookPrice(new User(UserLevel.GOLD), book));
    }

}

interface UserLevelService {
    double getBookPrice(final Book book);
    double getEpubPrice(final Epub epub);
}

class RegularUserLevel implements UserLevelService {

    @Override
    public double getBookPrice(final Book book) {
        return book.getPrice();
    }

    @Override
    public double getEpubPrice(final Epub epub) {
        return epub.getPrice();
    }
}

class SilverUserLevel implements UserLevelService {

    @Override
    public double getBookPrice(final Book book) {
        return book.getPrice() * 0.95;
    }

    @Override
    public double getEpubPrice(final Epub epub) {
        return epub.getPrice() * 0.95;
    }
}

class GoldUserLevel implements UserLevelService {

    @Override
    public double getBookPrice(final Book book) {
        return book.getPrice() * 0.85;
    }

    @Override
    public double getEpubPrice(final Epub epub) {
        return epub.getPrice() * 0.85;
    }
}

class PlatinumUserLevel implements UserLevelService {

    @Override
    public double getBookPrice(final Book book) {
        return book.getPrice() * 0.8;
    }

    @Override
    public double getEpubPrice(final Epub epub) {
        return epub.getPrice() * 0.8;
    }
}
