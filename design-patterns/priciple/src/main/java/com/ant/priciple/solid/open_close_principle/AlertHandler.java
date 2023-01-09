package com.ant.priciple.solid.open_close_principle;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/2/26 9:38 上午
 */
public abstract class AlertHandler {

    protected AlertRule alertRule;
    protected Notification notification;

    public AlertHandler(AlertRule alertRule, Notification notification) {
        this.alertRule = alertRule;
        this.notification = notification;
    }

    protected abstract void check(ApiStatInfo apiStatInfo);
}
