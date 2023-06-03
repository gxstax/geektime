package com.ant.priciple.solid.open_close_principle;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/2/26 3:35 下午
 */
public class Notification {

    public void notifyed(NotificationEmergencyLevel notificationEmergencyLevel, String message) {
        System.out.println("WARNING:" + notificationEmergencyLevel + ";错误信息:" + message);
    }
}
