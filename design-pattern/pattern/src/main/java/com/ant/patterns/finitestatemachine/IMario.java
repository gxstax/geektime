package com.ant.patterns.finitestatemachine;

/**
 * <p>
 * 马里奥接口
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:35 下午
 */
public interface IMario {

    State getName();

    void obtainMushRooom(MarioStateMachine marioStateMachine);
    void obtainFireFlower(MarioStateMachine marioStateMachine);
    void obtainCape(MarioStateMachine marioStateMachine);

    void meetMonster(MarioStateMachine marioStateMachine);
}
