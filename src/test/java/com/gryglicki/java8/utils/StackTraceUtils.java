package com.gryglicki.java8.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Michał Gryglicki, PL on 24/08/16.
 */
public class StackTraceUtils {

    public static int countMatchesInStackTrace(Throwable throwable, String match) {
        String stackTrace = stackTraceAsString(throwable);
        return StringUtils.countMatches(stackTrace, match);
    }

    private static String stackTraceAsString(Throwable throwable) {
        ByteArrayOutputStream stackTraceOutputStream = new ByteArrayOutputStream();
        PrintStream stackTracePs = new PrintStream(stackTraceOutputStream);
        throwable.printStackTrace(stackTracePs);
        return stackTraceOutputStream.toString();
    }
}
