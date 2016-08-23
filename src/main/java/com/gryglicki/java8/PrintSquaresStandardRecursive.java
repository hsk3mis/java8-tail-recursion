package com.gryglicki.java8;

import java.io.PrintStream;

/**
 * Created by Michał Gryglicki, PL on 23/08/16.
 */
public class PrintSquaresStandardRecursive {

    public static void printSquaresUntil(int max, PrintStream ps) {
        printSquaresWithExceptionUntil(max, max+1, ps);
    }

    public static void printSquaresWithExceptionUntil(int max, int exceptionTreshold, PrintStream ps) {
        squareAndPrintWithException(1, max, exceptionTreshold, ps);
    }

    private static void squareAndPrintWithException(int i, int max, int exceptionTreshold, PrintStream ps) {
        ps.print(i * i + " ");
        if (i == exceptionTreshold) {
            throw new RuntimeException("Exception threshold reached!");
        }
        if (i < max) {
            squareAndPrintWithException(i + 1, max, exceptionTreshold, ps);
        }
    }

}