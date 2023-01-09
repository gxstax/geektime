package com.ant.priciple.solid.open_close_principle;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/2/26 9:38 上午
 */
public class TpsAlertHandler extends AlertHandler {

    public TpsAlertHandler(AlertRule alertRule, Notification notification) {
        super(alertRule, notification);
    }

    @Override
    protected void check(ApiStatInfo apiStatInfo) {
        notification.notifyed(NotificationEmergencyLevel.URGENCY, "TpsAlertHandler");
    }
}
