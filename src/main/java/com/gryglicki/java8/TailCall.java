package com.gryglicki.java8;

/**
 * Need to implement tail call optimization.
 * Created by Michał Gryglicki, PL on 23/08/16.
 */
@FunctionalInterface
public interface TailCall {
    TailCall get();

    default boolean terminated() { return false; }
}
