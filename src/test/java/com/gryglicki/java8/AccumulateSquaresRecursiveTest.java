package com.gryglicki.java8;

import org.junit.Test;

import static com.gryglicki.java8.utils.AccumulatedResultsUtils.numberOfAccumulatedResults;
import static com.gryglicki.java8.utils.StackTraceUtils.countMatchesInStackTrace;
import static org.junit.Assert.assertEquals;

/**
 * Test of tail recursive optimized versions that accumulate result.
 * Created by Michał Gryglicki, PL on 24/08/16.
 */
public class AccumulateSquaresRecursiveTest {
    @Test
    public void accumulate_squares_using_tail_recursion_should_work() {
        //Given
        int max = 25;
        //When
        String resultAccumulator = AccumulateSquaresTailRecursive.accumulateSquaresUntil(max);
        //Then
        assertEquals(max, numberOfAccumulatedResults(resultAccumulator));
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

    @Test
    public void mutable_accumulate_squares_using_tail_recursion_should_work() {
        //Given
        int max = 25;
        //When
        String resultAccumulator = AccumulateUsingMutableAccumulatorSquaresTailRecursive.accumulateSquaresUntil(max);
        //Then
        assertEquals(max, numberOfAccumulatedResults(resultAccumulator));
    }

    @Test
    public void mutable_accumulate_squares_using_tail_recursion_should_have_short_stacktrace() {
        //Given
        int max = 25;
        int exceptionThreshold = 20;
        //When
        try {
            String resultAccumulator = AccumulateUsingMutableAccumulatorSquaresTailRecursive.accumulateSquaresUntil(max, exceptionThreshold);
        } catch (RuntimeException ex) {
            //Then
            assertEquals(1, countMatchesInStackTrace(ex, "AccumulateUsingMutableAccumulatorSquaresTailRecursive.accumulateSquare("));
        }
    }

}
