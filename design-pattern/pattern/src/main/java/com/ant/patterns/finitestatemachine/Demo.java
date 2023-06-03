package com.ant.patterns.finitestatemachine;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:46 下午
 */
public class Demo {

    public static void main(String[] args) {
        MarioStateMachine marioStateMachine = new MarioStateMachine(0);

        marioStateMachine.obtainMushRooom();
        System.out.println("score-->" + marioStateMachine.getScore() + "state-->" + marioStateMachine.getCurrentState().getName());

        marioStateMachine.obtainFireFlower();
        System.out.println("score-->" + marioStateMachine.getScore() + "state-->" + marioStateMachine.getCurrentState().getName());

        marioStateMachine.obtainCape();
        System.out.println("score-->" + marioStateMachine.getScore() + "state-->" + marioStateMachine.getCurrentState().getName());

        marioStateMachine.meetMonster();
        System.out.println("score-->" + marioStateMachine.getScore() + "state-->" + marioStateMachine.getCurrentState().getName());

        marioStateMachine.obtainCape();
        System.out.println("score-->" + marioStateMachine.getScore() + "state-->" + marioStateMachine.getCurrentState().getName());

    }
}
