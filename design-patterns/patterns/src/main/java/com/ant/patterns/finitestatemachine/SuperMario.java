package com.ant.patterns.finitestatemachine;

/**
 * <p>
 * 超级马里奥
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:38 下午
 */
public class SuperMario implements IMario {

    private static final SuperMario instance = new SuperMario();
    private SuperMario() {}

    public static SuperMario getInstance() {
        return instance;
    }
    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRooom(MarioStateMachine marioStateMachine) {
        marioStateMachine.setScore(marioStateMachine.getScore());
        marioStateMachine.setCurrentState(SuperMario.getInstance());
    }

    @Override
    public void obtainFireFlower(MarioStateMachine marioStateMachine) {
        marioStateMachine.setScore(marioStateMachine.getScore() + 200);
        marioStateMachine.setCurrentState(FIreMario.getInstance());

    }

    @Override
    public void obtainCape(MarioStateMachine marioStateMachine) {
        marioStateMachine.setScore(marioStateMachine.getScore() + 300);
        marioStateMachine.setCurrentState(CapeMario.getInstance());
    }

    @Override
    public void meetMonster(MarioStateMachine marioStateMachine) {
        marioStateMachine.setScore(marioStateMachine.getScore() - 200);
        marioStateMachine.setCurrentState(SmallMario.getInstance());
    }
}
