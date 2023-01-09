package com.ant.priciple.solid.open_close_principle;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/2/26 9:38 上午
 */
public class ErrorAlertHandler extends AlertHandler{

    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    protected void check(ApiStatInfo apiStatInfo) {
        notification.notifyed(NotificationEmergencyLevel.URGENCY, "ErrorAlertHandler");
    }
}
