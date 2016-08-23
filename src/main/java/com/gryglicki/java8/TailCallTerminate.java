package com.gryglicki.java8;

/**
 * Created by Michał Gryglicki, PL on 23/08/16.
 */
public class TailCallTerminate implements TailCall {
    public TailCall get() {
        throw new UnsupportedOperationException("Don't call get on terminate tail call");
    }

    public boolean terminated() {
        return true;
    }
}
