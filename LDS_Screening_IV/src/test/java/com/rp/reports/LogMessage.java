package com.rp.reports;

import lombok.Builder;
import lombok.Data;

/**
 * A log message for the Report Logger.
 */
@Data
@Builder
public class LogMessage {
    private ReportLogSeverity severity;
    private String timestamp;
    private String description;
    private String expected;
    private String actual;
    private String detail;
    private String timeDelta;
}
