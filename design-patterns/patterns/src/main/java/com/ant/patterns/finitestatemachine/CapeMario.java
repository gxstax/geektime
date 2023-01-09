package com.ant.patterns.finitestatemachine;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:38 下午
 */
public class CapeMario implements IMario{

    private static final CapeMario instance = new CapeMario();

    private CapeMario() {}

    public static CapeMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.CAPE;
    }

    @Override
    public void obtainMushRooom(MarioStateMachine marioStateMachine) {
        // doNothing
    }

    @Override
    public void obtainFireFlower(MarioStateMachine marioStateMachine) {
        // doNothing
    }

    @Override
    public void obtainCape(MarioStateMachine marioStateMachine) {
        // doNothing
    }

    @Override
    public void meetMonster(MarioStateMachine marioStateMachine) {
        marioStateMachine.setScore(marioStateMachine.getScore() - 200);
        marioStateMachine.setCurrentState(SmallMario.getInstance());
    }
}
