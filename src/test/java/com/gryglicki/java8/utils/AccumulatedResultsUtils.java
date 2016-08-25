package com.gryglicki.java8.utils;

/**
 * Created by Michał Gryglicki, PL on 24/08/16.
 */
public class AccumulatedResultsUtils {

    public static int numberOfAccumulatedResults(String output) {
        return output.trim().split(" ").length;
    }

}
