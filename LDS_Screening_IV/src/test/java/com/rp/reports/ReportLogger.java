package com.rp.reports;

import org.apache.commons.text.StringEscapeUtils;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A utility to log information
 */
public class ReportLogger {

    /**
     * Log messages collection.
     */
    private ArrayList<LogMessage> _logMessages;

    /**
     * Elapsed
     */
    private long _currentTimestamp;

    /**
     * The name of the test.
     */
    private String _testName;

    /**
     * Ctor
     */
    public ReportLogger(String testName) {
        _testName = "TEST METHOD: " + testName;
        _logMessages = new ArrayList<>();
        _currentTimestamp = System.currentTimeMillis();
    }

    /**
     * Add an information log message to the test execution report.
     *
     * @param description Description.
     * @param expected    Expected value for the test.
     * @param actual      Actual value received from the test.
     * @param detail      Test log detail.
     */
    public void info(String description, String expected, String actual, String detail) {
        addMessage(ReportLogSeverity.Info, description, expected, actual, detail);
    }

    /**
     * And an error log message to the test execution report.
     *
     * @param description Description.
     * @param expected    Expected value for the test.
     * @param actual      Actual value received from the test.
     * @param detail      Test log detail.
     */
    public void error(String description, String expected, String actual, String detail) {
        addMessage(ReportLogSeverity.Error, description, expected, actual, detail);
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
    public void addMessage(ReportLogSeverity severity, String description, String expected, String actual, String detail) {
        LogMessage message = LogMessage.builder()
                .severity(severity)
                .timestamp(computeTimestamp())
                .description(description)
                .expected(expected)
                .actual(actual)
                .detail(detail)
                .timeDelta(computeElapsed())
                .build();

        _logMessages.add(message);
    }

    public String buildReport(ITestResult testResult) {
        String reportBody = "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n" +
                "        integrity=\"sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor\" crossorigin=\"anonymous\">\n" +
                "    <title>Sample Report</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" + constructReportHeader(testResult) +
                "\n";

        for(LogMessage logMessage : _logMessages) {
            String cardHtml = constructReportCard(logMessage);
            reportBody = reportBody + cardHtml;
        }

        reportBody = reportBody + "</hr>" + constructReportFooter(testResult);

        return reportBody;
    }

    public String constructReportCard(LogMessage logMessage) {
        String headerCssClass = "card-header text-bg-success mb-3";

        switch(logMessage.getSeverity()) {
            case Error:
                headerCssClass = "card-header text-bg-danger mb-3";
                break;

            case Warn:
                headerCssClass = "card-header text-bg-warning mb-3";
                break;

            default:
                headerCssClass = "card-header text-bg-success mb-3";
                break;
        }


        String cardHtml = "    <div class=\"card\">\n" +
                "        <div class=\"" + headerCssClass + "\"><h5>" + StringEscapeUtils.escapeHtml4(logMessage.getDescription()) + "</h5></div>\n" +
                "        <div class=\"card-body\">\n" +
                "            <p class=\"card-text\">\n" +
                "            <table class=\"table\">\n" +
                "                <thead>\n" +
                "                    <tr>\n" +
                "                        <th>Category</th>\n" +
                "                        <th>Value</th>\n" +
                "                    </tr>\n" +
                "                </thead>\n" +
                "                <tbody>\n" +
                "                    <tr>\n" +
                "                        <td><strong>Timestamp</strong></td>\n" +
                "                        <td class=\"font-monospace\">" + StringEscapeUtils.escapeHtml4(logMessage.getTimestamp()) + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td><strong>Input Value</strong></td>\n" +
                "                        <td class=\"font-monospace\">" + StringEscapeUtils.escapeHtml4(logMessage.getDetail()) + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td><strong>Expected</strong></td>\n" +
                "                        <td class=\"font-monospace\">" + StringEscapeUtils.escapeHtml4(logMessage.getExpected()) + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td><strong>Actual</strong></td>\n" +
                "                        <td class=\"font-monospace\">" + StringEscapeUtils.escapeHtml4(logMessage.getActual()) + "</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td><strong>Timing</strong></td>\n" +
                "                        <td class=\"font-monospace\">" + StringEscapeUtils.escapeHtml4(logMessage.getTimeDelta()) + "</td>\n" +
                "                    </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            </p>\n" +
                "        </div>\n" +
                "    </div>\n";

        return cardHtml;
    }
    /**
     * Construct the header HTML for the report.
     *
     * @param testResult
     * @return
     */
    private String constructReportHeader(ITestResult testResult) {
        StringBuilder header = new StringBuilder();
        header.append("<div class=\"");
        header.append(testResult.isSuccess() ? "alert alert-success" : "alert alert-danger");
        header.append("\"><h2>");
        header.append(_testName);
        header.append("</h2>");

        if (!testResult.isSuccess() && testResult.getThrowable() != null) {
            header.append("<p>");
            header.append(testResult.getThrowable().getLocalizedMessage());
            header.append("</p>");
            testResult.getThrowable().printStackTrace();
        }
        header.append("</div><hr />");

        String headerText = header.toString();
        return headerText;
    }

    private String constructReportFooter(ITestResult testResult) {
        StringBuilder footer = new StringBuilder();
        if (!testResult.isSuccess() && testResult.getThrowable() != null)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            testResult.getThrowable().printStackTrace(pw);

            footer.append("<hr /><div class=\"card\">\n" +
                    "        <div class=\"card-header\">\n" + testResult.getThrowable().getLocalizedMessage() +
                    "        </div>\n" +
                    "        <div class=\"card-body\">\n");
            footer.append(System.lineSeparator());
            footer.append("<pre>");
            footer.append(System.lineSeparator());
            footer.append(sw.toString());
            footer.append(System.lineSeparator());
            footer.append("</pre>");
            footer.append(System.lineSeparator());
            footer.append("</div></div>");
        } else {
            footer.append("<hr />");
        }

        return footer.toString();
    }

    /**
     * Compute the current elapsed timestamp for the logs.
     *
     * @return Compute the current elapsed timestamp for the logs.
     */
    private String computeElapsed() {
        long timestamp = System.currentTimeMillis();
        long delta = timestamp - _currentTimestamp;
        _currentTimestamp = timestamp;

        String message = String.valueOf(delta) + " ms";
        return message;
    }

    /**
     * Compute the current timestamp.
     *
     * @return The current timestamp.
     */
    private String computeTimestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String timestamp = simpleDateFormat.format(new Date());
        return timestamp;
    }
}
