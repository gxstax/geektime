package com.ant.patterns.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p>
 * key1 > 100 规则表达式
 * </p>
 *
 * @author GaoXin
 * @since 2020/4/17 8:24 上午
 */
public class GreaterExpression implements Expression {

    private static final Logger log = LoggerFactory.getLogger(GreaterExpression.class);

    private String key;
    private long value;

    public GreaterExpression(String expressionStr) {
        String[] elements = expressionStr.trim().split("\\s+");
        if (elements.length != 3 || !elements[1].trim().equals(">")) {
            throw new RuntimeException("Expression is invalid: " + expressionStr);
        }
        this.key = elements[0].trim();
        this.value = Long.parseLong(elements[2].trim());
    }

    public GreaterExpression(String key, long value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        // 判断规则中是否有该规则
        if (!stats.containsKey(key)) {
            return false;
        }
        // 如果有该规则，则判断是否到了规则的告警阈值，到了则返回true（表示需要告警）
        long statValue = stats.get(key);
        boolean alert = statValue > value;
        log.info("GreaterExpression 是否需要告警[{}]", alert);
        return alert;
    }

//    public static void main(String[] args) {
//        String rule = "key1 > 100";
//        GreaterExpression greaterExpression = new GreaterExpression(rule);
//        System.out.println(greaterExpression);
//    }

    @Override
    public String toString() {
        return "GreaterExpression{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
