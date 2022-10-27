package com.ant.methodcall;


/**
 * <p>
 * 乘客
 * </P>
 *
 * @author Ant
 * @since 2022/10/26 4:32 下午
 **/
public abstract class Passenger {
    abstract void passThroughImmigration();

    @Override
    public String toString() {
        return "Passenger{}";
    }
}
