package com.rp.apis.functionallibrary;


import com.rp.reports.ResultLog;
import io.restassured.response.Response;
import lombok.Data;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.parser.ParseException;

@Data
public class ApiResponse<T> {

    T successResponse;
    String errorResponse;
    int httpStatusCode;
    String rawResponse;

    /**
     * Generic response for the APIs
     * @param responseBody - Response body
     * @param responseClass - Generic response class
     * @return - Api response as a operation result
     * @param <TResponse> - Response type
     * @throws ParseException
     */
    public static <TResponse> ApiResponse<TResponse> parseResponse(Response responseBody, Class<TResponse> responseClass)
            throws ParseException {

        ApiResponse<TResponse> operationResult = new ApiResponse<>();
        operationResult.setHttpStatusCode(responseBody.getStatusCode());
        operationResult.setRawResponse(responseBody.prettyPrint());

        JsonParser parser = JsonParser.create().ignoreUnknownBeanProperties().build();

        if (isSuccessCode(responseBody.getStatusCode())) {
            TResponse successResponse = parser.parse(responseBody.asString(), responseClass);
            operationResult.setSuccessResponse(successResponse);
        } else {
            ResultLog.notice("Error Response", "", "", responseBody.prettyPrint());
            operationResult.setErrorResponse(responseBody.prettyPrint());
        }
        return operationResult;

    }

    private static boolean isSuccessCode(int httpStatusCode) {
        return (httpStatusCode >= 200 && httpStatusCode < 300);
    }


}
