package com.ant.patterns.observer;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/3/24 8:39 上午
 */
public class RegNotificationObserver implements RegObserver {
    private PromotionService promotionService; // 依赖注入

    public RegNotificationObserver(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    public void handleRegSuccess(long userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                promotionService.issueNewUserExperienceCash(userId);
            }
        });
        thread.start();
    }
}
