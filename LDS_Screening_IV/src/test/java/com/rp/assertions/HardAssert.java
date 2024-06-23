package com.rp.assertions;

import com.rp.reports.ResultLog;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.serializer.SerializeException;
import org.assertj.core.api.Assertions;

public class HardAssert {

    public static <T> void assertEquals(T actual, T expected, String inputValue, String logMessage)
            throws SerializeException {

        try {
            Assertions.assertThat(actual).isNotNull().usingRecursiveComparison().isEqualTo(expected);
            ResultLog.passResults(
                    logMessage,
                    inputValue,
                    JsonSerializer.DEFAULT_READABLE.serialize(expected),
                    JsonSerializer.DEFAULT_READABLE.serialize(actual));
        } catch (AssertionError | SerializeException assertionError) {
            try {
                ResultLog.failResults(
                        logMessage,
                        inputValue,
                        JsonSerializer.DEFAULT_READABLE.serialize(expected),
                        "Test case failed due to following reasons" + assertionError.getLocalizedMessage());
            } catch (SerializeException e) {
                ResultLog.failResults(
                        logMessage,
                        inputValue,
                        expected.toString(),
                        "Test case failed due to following reasons" + e.getLocalizedMessage());

            }
            throw assertionError;
        }
    }

    public static <T> void assertNotEqualsEx(T actual, T expected, String inputValue, String logMessage)
            throws SerializeException {

        try {
            Assertions.assertThat(actual)
                      .isNotNull()
                      .usingRecursiveComparison()
                      .isNotEqualTo(expected);

            ResultLog.passResults(
                    logMessage,
                    inputValue,
                    JsonSerializer.DEFAULT_READABLE.serialize(expected),
                    JsonSerializer.DEFAULT_READABLE.serialize(actual));

        } catch (AssertionError | SerializeException assertionErr) {
            ResultLog.failResults(
                    logMessage,
                    inputValue,
                    JsonSerializer.DEFAULT_READABLE.serialize(expected),
                    "Test case failed due to following reasons" + assertionErr.getLocalizedMessage());
            throw assertionErr;
        }
    }

    public static void assertObjectNotNull(Object actual, String inputValue, String logMessage) {
        try {
            Assertions.assertThat(actual)
                      .isNotNull();

            ResultLog.passResults(
                    logMessage,
                    inputValue,
                    actual.toString(),
                    actual.toString());

        } catch (AssertionError assertionErr) {
            ResultLog.failResults(
                    logMessage,
                    inputValue,
                    null,
                    "Test case failed due to following reasons" + assertionErr.getLocalizedMessage());
            throw assertionErr;
        }
    }

    public static void assertIsTrue(boolean actual, String inputValue, String logMessage) {

        try {
            Assertions.assertThat(actual)
                      .isTrue();

            ResultLog.passResults(
                    logMessage,
                    inputValue,
                    "true",
                    String.valueOf(actual));

        } catch (AssertionError assertionErr) {
            ResultLog.failResults(
                    logMessage,
                    inputValue,
                    "true",
                    "Test case failed due to following reasons" + assertionErr.getLocalizedMessage());
            throw assertionErr;
        }

    }

}
