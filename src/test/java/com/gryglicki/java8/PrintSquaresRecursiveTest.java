package com.gryglicki.java8;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by Michał Gryglicki, PL on 24/08/16.
 */
public class PrintSquaresRecursiveTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream ps;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        ps = new PrintStream(outputStream);
    }

    @Test
    public void print_squares_using_stanard_recursion_should_work() {
        //Given
        int max = 25;
        //When
        PrintSquaresStandardRecursive.printSquaresUntil(max, ps);
        //Then
        String output = outputStream.toString();
        System.out.println(output);
    }

    @Test
    public void print_squares_using_stanard_recursion_should_have_long_stacktrace() {
        //Given
        int max = 25;
        int exceptionThreshold = 20;
        //When
        try {
            PrintSquaresStandardRecursive.printSquaresWithExceptionUntil(max, exceptionThreshold, ps);
        } catch (RuntimeException ex) {
            //Then
            assertTrue(countMatchesInStackTrace(ex, "PrintSquaresStandardRecursive.squareAndPrintWithException(") > 10);
        }
    }

    @Test
    public void print_squares_using_tail_recursion_should_work() {
        //Given
        int max = 25;
        //When
        PrintSquaresTailRecursive.printSquaresUntil(max, ps);
        //Then
        String output = outputStream.toString();
        System.out.println(output);
    }

    @Test
    public void print_squares_using_tail_recursion_should_have_short_stacktrace() {
        //Given
        int max = 25;
        int exceptionThreshold = 20;
        //When
        try {
            PrintSquaresTailRecursive.printSquaresWithExceptionUntil(max, exceptionThreshold, ps);
        } catch (RuntimeException ex) {
            //Then
            assertEquals(1, countMatchesInStackTrace(ex, "PrintSquaresTailRecursive.squareAndPrintWithException("));
        }
    }

    @Test
    public void accumulate_squares_using_tail_recursion_should_work() {
        //Given
        int max = 25;
        //When
        String resultAccumulator = AccumulateSquaresTailRecursive.accumulateSquaresUntil(max);
        //Then
        System.out.println(resultAccumulator);
    }

    @Test
    public void accumulate_squares_using_tail_recursion_should_have_short_stacktrace() {
        //Given
        int max = 25;
        int exceptionThreshold = 20;
        //When
        try {
            String resultAccumulator = AccumulateSquaresTailRecursive.accumulateSquaresUntil(max, exceptionThreshold);
        } catch (RuntimeException ex) {
            //Then
            assertEquals(1, countMatchesInStackTrace(ex, "AccumulateSquaresTailRecursive.accumulateSquare("));
        }
    }

    private int countMatchesInStackTrace(Throwable throwable, String match) {
        String stackTrace = stackTraceAsString(throwable);
        return StringUtils.countMatches(stackTrace, match);
    }

    private String stackTraceAsString(Throwable throwable) {
        ByteArrayOutputStream stackTraceOutputStream = new ByteArrayOutputStream();
        PrintStream stackTracePs = new PrintStream(stackTraceOutputStream);
        throwable.printStackTrace(stackTracePs);
        return stackTraceOutputStream.toString();
    }
}
