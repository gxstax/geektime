package com.ant.lesson07;

import lombok.Getter;

/**
 * <p>
 * 滥用控制语句示例 2
 * </p>
 *
 * @author Ant
 * @since 2021/1/14 5:56 下午
 */
public class ControlStatementDemo2 {

    private static final Integer START_CHARGING_SEQUENCE = 10;
    private static final Integer FURTHER_CHARGING_SEQUENCE = 10;

    private Integer sequenceNumber;


    public double getBookPrice(final User user, final Book book) {
        double price = book.getPrice();
        switch (user.getLevel()) {
            case SILVER:
                return price * 0.9;
            case GOLD:
                return price * 0.8;
            case PLATINUM:
                return price * 0.75;
            default:
                return price;
        }
    }

    public double getEpubPrice(final User user, final Epub epub) {
        double price = epub.getPrice();
        switch (user.getLevel()) {
            case SILVER:
                return price * 0.95;
            case GOLD:
                return price * 0.85;
            case PLATINUM:
                return price * 0.8;
            default:
                return price;
        }
    }
}

class User {

    private UserLevel level;

    public User() {}

    public User(UserLevel userLevel) {
        this.level = userLevel;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }
}

class Book {

    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

class Epub {

    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

@Getter
enum UserLevel {

    SILVER,
    GOLD,
    PLATINUM

    ;

}

