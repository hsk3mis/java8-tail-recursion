package com.gryglicki.java8;

import java.io.PrintStream;
import java.util.stream.Stream;

/**
 * Tail recursive implementation that prints to the stream - don't accumulate the result.
 * Created by Michał Gryglicki, PL on 23/08/16.
 */
public class PrintSquaresTailRecursive {

    public static void printSquaresUntil(int max, PrintStream ps) {
        printSquaresWithExceptionUntil(max, max+1, ps);
    }

    public static void printSquaresWithExceptionUntil(int max, int exceptionTreshold, PrintStream ps) {
        Stream.iterate(squareAndPrintWithException(1, max, exceptionTreshold, ps), TailCall::get)
                .filter(TailCall::terminated)
                .findFirst();
    }

    private static TailCall squareAndPrintWithException(int i, int max, int exceptionTreshold, PrintStream ps) {
        ps.print(i * i + " ");
        if (i == exceptionTreshold) {
            throw new RuntimeException("Exception threshold reached!");
        }
        if (i < max) {
            return () -> squareAndPrintWithException(i + 1, max, exceptionTreshold, ps);
        } else {
            return new TailCallTerminate();
        }
    }

}