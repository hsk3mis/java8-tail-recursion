package com.gryglicki.java8;

/**
 * Needed to terminate tail call optimization and keep accumulated result.
 * Created by Michał Gryglicki, PL on 23/08/16.
 */
public class TailCallTerminateWithAccumulator implements TailCall {

    private final String accumulator;
    public TailCallTerminateWithAccumulator(String accumulator) {
        this.accumulator = accumulator;
    }

    public String getAccumulator() {
        return this.accumulator;
    }

    public TailCall get() {
        throw new UnsupportedOperationException("Don't call get on terminate tail call");
    }

    public boolean terminated() {
        return true;
    }
}
