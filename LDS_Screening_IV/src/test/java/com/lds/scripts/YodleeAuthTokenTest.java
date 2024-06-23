package com.lds.scripts;

import com.rp.apis.functionallibrary.ApiResponse;
import com.rp.apis.functionallibrary.YodleeAuthTokenHub;
import com.rp.assertions.HardAssert;
import com.rp.base.ApiBase;
import com.lds.models.api.*;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.Map;

public class YodleeAuthTokenTest extends ApiBase
{

    private @Autowired YodleeAuthTokenHub _YodleeAuthTokenHub;


    @Description("Verify YodleeAuth Token  test")
    @Test(dataProvider = "YodleeAuthToken")
    public void verifyYodleeAuthToken(Map<String, String> data) throws ParseException, IOException, SerializeException
    {
        //Arrange
       // YodleeAuthTokenRequest createProcessOrderRequest=_YodleeAuthTokenbuilder.YodleeAuthTokenRequestBuilder();
        //act
        ApiResponse<YodleeAuthTokenResponse> YodleeRespone =_YodleeAuthTokenHub.createOrGetYodleeAuthTokenRequest();
//        System.out.println("Response is :");
//        System.out.println(YodleeRespone.getRawResponse());

        HardAssert.assertEquals(200, YodleeRespone.getHttpStatusCode(),
                "Status Code",
                "Verify Process Order status code");
        JsonPath j = new JsonPath(YodleeRespone.getRawResponse());
        ConsumerIdValue= j.getString("ConsumerId");
        TransactionIdValue= j.getString("TransactionId");
        IVVendorURLValue= j.getString("IVVendorURL");//
        IVVendorAccessTokenValue= j.getString("IVVendorAccessToken");




    }

}
