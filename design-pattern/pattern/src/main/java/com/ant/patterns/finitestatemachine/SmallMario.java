package com.ant.patterns.finitestatemachine;

/**
 * <p>
 * 正常马里奥
 * </p>
 *
 * @author GaoXin
 * @since 2020/4/1 8:37 下午
 */
public class SmallMario implements IMario{

    private static final SmallMario instance = new SmallMario();

    private SmallMario() {}
    public static SmallMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRooom(MarioStateMachine marioStateMachine) {
        marioStateMachine.setScore(marioStateMachine.getScore() + 100);
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
