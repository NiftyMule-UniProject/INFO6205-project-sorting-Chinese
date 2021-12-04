package edu.neu.coe.info6205.compSort.huskySort.utils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LazyLoggerTest {

    static class StringEvaluator {

        public boolean isEvaluated() {
            return evaluated;
        }

        boolean evaluated = false;

        String evaluateMessage(String message) {
            System.out.println("Evaluate message: " + message);
            evaluated = true;
            return message;
        }

    }

    public String evaluateMessage(String message) {
        System.out.println("evaluate message: " + message);
        return message;
    }

    @Test
    public void testTraceLazy() {
        StringEvaluator se = new StringEvaluator();
        logger.trace(() -> "Hello " + se.evaluateMessage("trace message"));
        assertEquals(logger.isTraceEnabled(), se.isEvaluated());
    }

    @SuppressWarnings("EmptyMethod")
    @Disabled
    public void testTraceLazyException() {
    }

    @Test
    public void testDebugLazy() {
        StringEvaluator se = new StringEvaluator();
        logger.debug(() -> "Hello " + se.evaluateMessage("debug message"));
        assertEquals(logger.isDebugEnabled(), se.isEvaluated());
    }

    @SuppressWarnings("EmptyMethod")
    @Disabled
    public void testDebugLazyException() {
    }

    static final LazyLogger logger = new LazyLogger(LazyLoggerTest.class);
}