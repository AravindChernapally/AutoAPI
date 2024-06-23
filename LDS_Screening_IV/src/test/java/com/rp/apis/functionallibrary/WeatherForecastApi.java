/*
package com.rp.apis.functionallibrary;

import com.rp.apis.apiconfig.Authentication;
import com.rp.apis.apiresources.RequestType;
import com.lds.models.api.WeatherForecast;
import com.rp.reports.ResultLog;
import com.rp.util.RestUtils;
import io.restassured.response.Response;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class WeatherForecastApi {

    private @Autowired RestUtils _restUtils;

    private final String _apiBaseUrl;

    private final JsonSerializer _jsonSerializer;

    private @Autowired Authentication _authentication;

    public WeatherForecastApi(String apiBaseUrl) {

        _apiBaseUrl = apiBaseUrl;
        _jsonSerializer = JsonSerializer.DEFAULT_READABLE;

    }

    public ApiResponse<WeatherForecast> getWeatherForecast() throws ParseException, IOException {

        //endpointUrl
        String endpointUrl = _apiBaseUrl + "/api/users?page=2";

        //Retrieve weather forecast
        Response responseBody = _restUtils.restApiResponse(
                endpointUrl,
                RequestType.GET,
                null,
                null);

        ResultLog.notice(
                "Request and Response for weather forecast",
                endpointUrl,
                "",
                responseBody.prettyPrint());

        //Response
        ApiResponse<WeatherForecast> response = ApiResponse.parseResponse(responseBody, WeatherForecast.class);

        return response;

    }
}
*/
