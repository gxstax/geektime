package com.ant.patterns.finitestatemachine;

/**
 * <p>
 * 马里奥状态机
 * </p>
 *
 * @author Ant
 * @since 2020/4/1 8:39 下午
 */
public class MarioStateMachine {
    private int score;

    private IMario currentState;

    public MarioStateMachine(int score) {
        this.score = score;
        this.currentState = SmallMario.getInstance();
    }


    public void obtainMushRooom() {
        currentState.obtainMushRooom(this);
    }

    public void obtainFireFlower() {
        currentState.obtainFireFlower(this);
    }

    public void obtainCape() {
        currentState.obtainCape(this);
    }

    public void meetMonster() {
        currentState.meetMonster(this);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public IMario getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }
}
