/*
package com.lds.scripts;

import com.rp.apis.functionallibrary.ApiResponse;
import com.rp.apis.functionallibrary.WeatherForecastApi;
import com.rp.assertions.HardAssert;
import com.rp.base.ApiBase;
import com.lds.models.api.WeatherForecast;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

@Epic ("Api")
@Feature ("Sample Api")
public class WeatherForecastApiTest extends ApiBase {

    private @Autowired WeatherForecastApi _weatherforecastApi;

    @Description ("Verify weather forecast api test")
    @Test (dataProvider = "SampleTestData")
    public void verifyWeatherForecastApi(Map<String, String> data)
            throws ParseException, IOException, SerializeException {

        //Arrange

        //Act
        ApiResponse<WeatherForecast> actual = _weatherforecastApi.getWeatherForecast();

        //Assert

        HardAssert.assertObjectNotNull(
                actual.getSuccessResponse(),
                "Success Response Object",
                "Verify response object is not null");

        HardAssert.assertEquals(
                actual.getHttpStatusCode(),
                200,
                "Http Status Code",
                "Verify Status Code for the Api");


    }


}
*/
