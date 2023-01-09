package com.ant.patterns.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 告警规则解释器
 * </p>
 *
 * @author GaoXin
 * @since 2020/4/17 9:03 上午
 */
public class AlertRuleInterpreter {
    private Expression expression;

    public AlertRuleInterpreter(String ruleExpression) {
        // 这里实例化一个OrExpression类，因为Or包括And，And包括其它，集体可以到各表达式类的构造函数中一探究竟
        this.expression = new OrExpression(ruleExpression);
    }

    public boolean interpret(Map<String, Long> stats) {
        return expression.interpret(stats);
    }

    public static void main(String[] args) {
        // 这个是我们自己设定的规则制度
        String selfRuleExpression = "key1 > 100 && key2 < 30 || key3 == 100";
        AlertRuleInterpreter alertRuleInterpreter = new AlertRuleInterpreter(selfRuleExpression);

        // 模拟接口传给我们判断的数据
        Map<String, Long> stats = new HashMap<>();
        stats.put("key1", 101L);
        stats.put("key2", 100L);
        stats.put("key3", 100L);

        alertRuleInterpreter.interpret(stats);
    }
}
