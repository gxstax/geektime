package com.ant.patterns.observer.event_bus;

import com.google.common.eventbus.Subscribe;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/3/24 8:39 上午
 */
public class RegNotificationObserver {
    private PromotionService promotionService; // 依赖注入

    public RegNotificationObserver(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Subscribe
    public void handleRegSuccess(Long userId) throws InterruptedException {
        System.out.println("执行了");
        Thread.sleep(1000L);
        promotionService.issueNewUserExperienceCash(userId);
    }
}
