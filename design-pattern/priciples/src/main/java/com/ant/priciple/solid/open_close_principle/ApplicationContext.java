package com.ant.priciple.solid.open_close_principle;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/2/26 3:54 下午
 */
public class ApplicationContext {

    private static final ApplicationContext instance = new ApplicationContext();

    private Alert alert;
    private AlertRule alertRule;
    private Notification notification;

    public void initializeBeans() {
        alertRule = new AlertRule();
        notification = new Notification();
        alert = new Alert();

        alert.addAlert(new ErrorAlertHandler(alertRule, notification));
        alert.addAlert(new TpsAlertHandler(alertRule, notification));
    }

    private ApplicationContext() {
        initializeBeans();
    }

    private Alert getAlert() {
        return alert;
    }

    public static ApplicationContext getInstance() {
        return instance;
    }


    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();

        ApplicationContext.getInstance().getAlert().check(apiStatInfo);
    }
}
