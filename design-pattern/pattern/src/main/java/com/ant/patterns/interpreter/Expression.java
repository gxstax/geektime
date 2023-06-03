package com.ant.patterns.interpreter;

import java.util.Map;

/**
 * <p>
 * 解释器接口
 * </p>
 *
 * @author GaoXin
 * @since 2020/4/17 8:23 上午
 */
public interface Expression {
    boolean interpret(Map<String, Long> stats);
}
