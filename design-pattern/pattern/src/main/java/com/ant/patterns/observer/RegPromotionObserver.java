package com.ant.patterns.observer;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2020/3/24 8:38 上午
 */
public class RegPromotionObserver implements RegObserver {
    private PromotionService promotionService;

    public RegPromotionObserver(PromotionService promotionService) {
        this.promotionService = promotionService;
    }
    @Override
    public void handleRegSuccess(long userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                promotionService.sendInboxMessage(userId);
            }
        });
        thread.start();
    }
}
