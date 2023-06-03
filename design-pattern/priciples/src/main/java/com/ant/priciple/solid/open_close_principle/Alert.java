package com.ant.priciple.solid.open_close_principle;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 开闭原则demo
 * </p>
 *
 * @author Ant
 * @since 2020/2/26 9:01 上午
 */

public class Alert {
    private List<AlertHandler> alertHandlerList = new ArrayList<>();

    public void addAlert(AlertHandler alertHandler) {
        this.alertHandlerList.add(alertHandler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler alertHandler : alertHandlerList) {
            alertHandler.check(apiStatInfo);
        }
    }
}
