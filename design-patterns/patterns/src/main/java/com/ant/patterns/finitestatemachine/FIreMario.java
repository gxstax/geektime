package com.ant.patterns.finitestatemachine;

/**
 * <p>
 * 火花马里奥
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:37 下午
 */
public class FIreMario implements IMario{
    private static final FIreMario instance = new FIreMario();

    private FIreMario() {}

    public static FIreMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.FIRE;
    }

    @Override
    public void obtainMushRooom(MarioStateMachine marioStateMachine) {
        // doNothint
    }

    @Override
    public void obtainFireFlower(MarioStateMachine marioStateMachine) {
        // doNothing
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
