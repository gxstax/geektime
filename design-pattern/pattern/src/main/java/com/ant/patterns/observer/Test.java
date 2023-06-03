package com.ant.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/3/24 8:50 上午
 */
public class Test {

    public static void main(String[] args) {
        PromotionService promotionService = new PromotionService();
        // 券发放观察者
        RegNotificationObserver regNotificationObserver = new RegNotificationObserver(promotionService);
        RegPromotionObserver regPromotionObserver = new RegPromotionObserver(promotionService);

        List<RegObserver> observers = new ArrayList<>();
        observers.add(regNotificationObserver);
        observers.add(regPromotionObserver);

        UserController userController = new UserController(observers);

        userController.register(100001);

    }
}
