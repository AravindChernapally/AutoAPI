package com.rp.apis.functionallibrary;


import com.rp.apis.apiconfig.Authentication;
import com.rp.apis.apiresources.RequestType;
import com.lds.models.api.*;
import com.rp.reports.ResultLog;
import com.rp.util.RestUtils;
import io.restassured.response.Response;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import static com.rp.base.ApiBase.YodleeRequest;

public class YodleeAuthTokenHub{

    private @Autowired RestUtils _restUtils;

    private final String YodleeAuthTokenUrl;

    private final JsonSerializer _jsonSerializer;

    private @Autowired Authentication _authentication;

    public YodleeAuthTokenHub(String YodleeAuthTokenHubUrl) {

        YodleeAuthTokenUrl = YodleeAuthTokenHubUrl;
        _jsonSerializer = JsonSerializer.DEFAULT_READABLE;

    }
    public  ApiResponse<YodleeAuthTokenResponse> createOrGetYodleeAuthTokenRequest() throws ParseException {

        String AuthorizeToken= _authentication.getBearerToken();
       // System.out.println("Token is "+AuthorizeToken);
        //endpointUrl
        String endpointUrl = "https://p5y894pmn5.execute-api.us-east-2.amazonaws.com/qa/authtoken";
       // System.out.println(endpointUrl);
        //Build a request
        String jsonRequest = YodleeRequest;
       // System.out.println(jsonRequest);
        //Retrieve weather forecast
        Response responseBody = _restUtils.restApiResponse(
                YodleeAuthTokenUrl,
                RequestType.POST,
                AuthorizeToken,
                jsonRequest,"AuthorizeToken");

        ResultLog.notice(
                "Request and Response for YodlessAuthToken forecast",
                "URL "+endpointUrl,
                "Request "
                        +jsonRequest,
                "Response "
                        +responseBody.prettyPrint());

        //Response
        ApiResponse<YodleeAuthTokenResponse> response = ApiResponse.parseResponse(responseBody, YodleeAuthTokenResponse.class);
       // System.out.println("response is " + response);
        return response;

    }
}
