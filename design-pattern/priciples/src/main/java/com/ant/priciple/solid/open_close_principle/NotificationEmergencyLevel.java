package com.ant.priciple.solid.open_close_principle;

/**
 * <p>
 * 警告级别
 * </p>
 *
 * @author Ant
 * @since 2020/2/26 3:41 下午
 */
public enum NotificationEmergencyLevel {
    URGENCY(0, "紧急");

    private Integer level;
    private String levelMsg;

    NotificationEmergencyLevel(Integer level, String levelMsg) {
        this.level = level;
        this.levelMsg = levelMsg;
    }
}
