package com.rp.reports;

import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.HashMap;

public class TestLogger {
    /**
     * The collection of report loggers hashed by the hash of the TestNG test reporter result.
     */
    private static HashMap<String, ReportLogger> _reportLoggers = new HashMap<String, ReportLogger>();

    /**
     * Add an information log message to the test execution report.
     *
     * @param description Description.
     * @param expected    Expected value for the test.
     * @param actual      Actual value received from the test.
     * @param detail      Test log detail.
     */
    public static void info(String description, String expected, String actual, String detail) {
        addMessage(ReportLogSeverity.Info, description, expected, actual, detail);
    }

    /**
     * Add a warning log message to the test execution report.
     *
     * @param description
     * @param expected
     * @param actual
     * @param detail
     */
    public static void warn(String description, String expected, String actual, String detail) {
        addMessage(ReportLogSeverity.Warn, description, expected, actual, detail);
    }

    /**
     * And an error log message to the test execution report.
     *
     * @param description Description.
     * @param expected    Expected value for the test.
     * @param actual      Actual value received from the test.
     * @param detail      Test log detail.
     */
    public static void error(String description, String expected, String actual, String detail) {
        addMessage(ReportLogSeverity.Error, description, expected, actual, detail);
    }

    /**
     * Build the report for the current test context.
     * @return
     */
    public static String buildReport(ITestResult testResult) {
        String testId = testResult.id();
        if (!_reportLoggers.containsKey(testId))
        {
            String testName = Reporter.getCurrentTestResult().getMethod().getMethodName();
            _reportLoggers.put(testId, new ReportLogger(testName));
        }

        String reportContent = _reportLoggers.get(testId).buildReport(testResult);
        return reportContent;
    }

    /**
     * Internal message logger.
     *
     * @param severity    Log severity.
     * @param description Description.
     * @param expected    Expected value for the test.
     * @param actual      Actual value received from the test.
     * @param detail      Test log detail.
     */
    private static void addMessage(ReportLogSeverity severity, String description, String expected, String actual, String detail) {
        String testId = Reporter.getCurrentTestResult().id();
        if (!_reportLoggers.containsKey(testId))
        {
            String testName = Reporter.getCurrentTestResult().getMethod().getMethodName();
            _reportLoggers.put(testId, new ReportLogger(testName));
        }
        _reportLoggers.get(testId).addMessage(severity, description, expected, actual, detail);
    }
}