package com.ant.patterns.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * key1 > 100 && key2 < 30 规则表达式
 * </p>
 *
 * @author GaoXin
 * @since 2020/4/17 8:43 上午
 */
public class AndExpression implements Expression {

    private static final Logger log = LoggerFactory.getLogger(AndExpression.class.getName());

    // 表达式列表
    private List<Expression> expressions = new ArrayList<>();

    public AndExpression(String andExpressStr) {
        String[] expressionStrs = andExpressStr.trim().split("&&");
        for (String expressionStr : expressionStrs) {
            if (expressionStr.contains(">")) {
                expressions.add(new GreaterExpression(expressionStr));
            } else if (expressionStr.contains("<")) {
                expressions.add(new LessExpression(expressionStr));
            } else if (expressionStr.contains("==")) {
                expressions.add(new EqualExpression(expressionStr));
            } else {
                throw new RuntimeException("Expression is invalid: " + andExpressStr);
            }
        }
    }

    public AndExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (Expression expression : expressions) {
            // 这里的 && 操作只要有一个不符合，后面的不需要判断直接返回
            if (!expression.interpret(stats)) {
                return false;
            }
        }
        return true;
    }
}
