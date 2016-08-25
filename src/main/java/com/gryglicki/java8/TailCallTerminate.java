package com.gryglicki.java8;

/**
 * Needed to terminate tail call optimization.
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
