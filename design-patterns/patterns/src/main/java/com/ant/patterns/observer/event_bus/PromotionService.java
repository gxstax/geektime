package com.ant.patterns.observer.event_bus;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/3/24 8:40 上午
 */
public class PromotionService {
    // 发放经验值礼券
    public void issueNewUserExperienceCash(long userId) {
        System.out.println("issueNewUserExperienceCash--> userId:" + userId);
    }

    // 发站内信
    public void sendInboxMessage(long userId) {
        System.out.println("sendInboxMessage--> userId:" + userId);
    }
}
