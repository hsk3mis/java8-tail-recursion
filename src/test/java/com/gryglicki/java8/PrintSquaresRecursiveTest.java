package com.gryglicki.java8;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.gryglicki.java8.utils.AccumulatedResultsUtils.numberOfAccumulatedResults;
import static com.gryglicki.java8.utils.StackTraceUtils.countMatchesInStackTrace;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test of simple and tail recursive optimized versions that print to the stream.
 * Created by Michał Gryglicki, PL on 24/08/16.
 */
public class PrintSquaresRecursiveTest {

    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void print_squares_using_stanard_recursion_should_work() {
        //Given
        int max = 25;
        //When
        try (PrintStream ps = new PrintStream(outputStream)) {
            PrintSquaresStandardRecursive.printSquaresUntil(max, ps);
        }
        //Then
        assertEquals(max, numberOfAccumulatedResults(outputStream.toString()));
    }

    @Test
    public void print_squares_using_stanard_recursion_should_have_long_stacktrace() {
        //Given
        int max = 25;
        int exceptionThreshold = 20;
        //When
        try (PrintStream ps = new PrintStream(outputStream)) {
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
        try (PrintStream ps = new PrintStream(outputStream)) {
            PrintSquaresTailRecursive.printSquaresUntil(max, ps);
        }
        //Then
        assertEquals(max, numberOfAccumulatedResults(outputStream.toString()));
    }

    @Test
    public void print_squares_using_tail_recursion_should_have_short_stacktrace() {
        //Given
        int max = 25;
        int exceptionThreshold = 20;
        //When
        try (PrintStream ps = new PrintStream(outputStream)) {
            PrintSquaresTailRecursive.printSquaresWithExceptionUntil(max, exceptionThreshold, ps);
        } catch (RuntimeException ex) {
            //Then
            assertEquals(1, countMatchesInStackTrace(ex, "PrintSquaresTailRecursive.squareAndPrintWithException("));
        }
    }

}
