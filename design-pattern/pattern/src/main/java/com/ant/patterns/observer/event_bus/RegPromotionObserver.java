package com.ant.patterns.observer.event_bus;

import com.google.common.eventbus.Subscribe;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/3/24 8:38 上午
 */
public class RegPromotionObserver {
    private PromotionService promotionService;

    public RegPromotionObserver(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Subscribe
    public void handleRegSuccess(Long userId) throws InterruptedException {
        System.out.println("执行了");
        Thread.sleep(1000L);
        promotionService.sendInboxMessage(userId);
    }

    @Subscribe
    public void test(long userId) {
        System.out.println("fuck");
    }

}
