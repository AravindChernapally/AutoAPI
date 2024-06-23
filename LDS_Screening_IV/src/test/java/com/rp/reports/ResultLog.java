package com.rp.reports;

public class ResultLog {

    public static synchronized void passResults(String description, String inputValue, String expectedValue, String actualValue) {
        System.out.println("PASS: Description: " + description);
        System.out.println("PASS: InputValue: " + inputValue);
        System.out.println("PASS: ExpectedValue: " + expectedValue);
        System.out.println("PASS: ActualValue: " + actualValue);

        TestLogger.info(description, expectedValue, actualValue, inputValue);
    }

    public static synchronized void failResults(String description, String inputValue, String expectedValue, String actualValue) {
        System.out.println("FAIL: Description: " + description);
        System.out.println("FAIL: InputValue: " + inputValue);
        System.out.println("FAIL: ExpectedValue: " + expectedValue);
        System.out.println("FAIL: ActualValue: " + actualValue);

        TestLogger.error(description, expectedValue, actualValue, inputValue);

    }

    public static synchronized void info(String description, String inputValue, String expectedValue, String actualValue) {
        System.out.println("INFO: Description: " + description);
        System.out.println("INFO: InputValue: " + inputValue);
        System.out.println("INFO: ExpectedValue: " + expectedValue);
        System.out.println("INFO: ActualValue: " + actualValue);

        TestLogger.info(description, expectedValue, actualValue, inputValue);
    }

    public static synchronized void warning(String description, String inputValue, String expectedValue, String actualValue) {
        System.out.println("WARN: Description: " + description);
        System.out.println("WARN: InputValue: " + inputValue);
        System.out.println("WARN: ExpectedValue: " + expectedValue);
        System.out.println("WARN: ActualValue: " + actualValue);

        TestLogger.warn(description, expectedValue, actualValue, inputValue);
    }

    public static synchronized void notice(String description, String inputValue, String expectedValue, String actualValue) {
        System.out.println("NOTICE: Description: " + description);
        System.out.println("NOTICE: InputValue: " + inputValue);
        System.out.println("NOTICE: ExpectedValue: " + expectedValue);
        System.out.println("NOTICE: ActualValue: " + actualValue);

        TestLogger.info(description, expectedValue, actualValue, inputValue);
    }

}
