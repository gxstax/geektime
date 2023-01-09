package com.ant.priciple.solid.open_close_principle;

/**
 * <p>
 * 告警规则
 * </p>
 *
 * @author Ant
 * @since 2020/2/26 9:40 上午
 */
public class AlertRule {
    // 最大错误次数
    private Integer maxErrorCount;

    public boolean getMatchedRule(String api) {
        return true;
    }
}
