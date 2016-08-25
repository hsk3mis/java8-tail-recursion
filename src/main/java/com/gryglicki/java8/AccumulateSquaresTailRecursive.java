package com.gryglicki.java8;

import java.util.stream.Stream;

/**
 * Tail Recursive squares implementation that uses accumulator to keep the result.
 * Created by Michał Gryglicki, PL on 23/08/16.
 */
public class AccumulateSquaresTailRecursive {

    public static String accumulateSquaresUntil(int max) {
        return accumulateSquaresUntil(max, max + 1);
    }

    public static String accumulateSquaresUntil(int max, int exceptionThreshold) {
        TailCall tailCallTerminateWithAccumulator = Stream.iterate(accumulateSquare(1, max, exceptionThreshold, ""), TailCall::get)
                .filter(TailCall::terminated)
                .findFirst()
                .get();
        String resultAccumulator = ((TailCallTerminateWithAccumulator) tailCallTerminateWithAccumulator).getAccumulator();
        return resultAccumulator;
    }

    private static TailCall accumulateSquare(int i, int max, int exceptionThreshold, String accumulator) {
        final String accumulatorWithAdditionalSquare = accumulator + " " + (i * i);
        if (i == exceptionThreshold) {
            throw new RuntimeException("Exception threshold reached!");
        }
        if (i < max) {
            return () -> accumulateSquare(i + 1, max, exceptionThreshold, accumulatorWithAdditionalSquare);
        } else {
            return new TailCallTerminateWithAccumulator(accumulatorWithAdditionalSquare);
        }
    }
}