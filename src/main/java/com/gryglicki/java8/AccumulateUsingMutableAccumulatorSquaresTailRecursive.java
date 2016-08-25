package com.gryglicki.java8;

import java.util.stream.Stream;

/**
 * Tail Recursive squares implementation that uses mutable accumulator to keep the result.
 * Please don't do this - this is just an example to check how it work with tail recursion and lambdas.
 * Created by Michał Gryglicki, PL on 23/08/16.
 */
public class AccumulateUsingMutableAccumulatorSquaresTailRecursive {

    public static String accumulateSquaresUntil(int max) {
        return accumulateSquaresUntil(max, max+1);
    }

    public static String accumulateSquaresUntil(int max, int exceptionThreshold) {
        StringBuilder mutableAccumulator = new StringBuilder();
        TailCall tailCallTerminateWithAccumulator = Stream.iterate(accumulateSquare(1, max, exceptionThreshold, mutableAccumulator), TailCall::get)
                .filter(TailCall::terminated)
                .findFirst()
        .get();
        return mutableAccumulator.toString();
    }

    private static TailCall accumulateSquare(int i, int max, int exceptionThreshold, StringBuilder mutableAccumulator) {
        mutableAccumulator.append(" ").append(i * i);
        if (i == exceptionThreshold) {
            throw new RuntimeException("Exception threshold reached!");
        }
        if (i < max) {
            return () -> accumulateSquare(i + 1, max, exceptionThreshold, mutableAccumulator);
        } else {
            return new TailCallTerminate();
        }
    }
}