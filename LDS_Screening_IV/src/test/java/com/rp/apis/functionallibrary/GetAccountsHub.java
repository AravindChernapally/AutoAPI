package com.rp.apis.functionallibrary;


import com.rp.apis.apiconfig.Authentication;
import com.rp.apis.apiresources.RequestType;
import com.lds.models.api.*;
import com.rp.reports.ResultLog;
import com.rp.util.RestUtils;
import io.restassured.response.Response;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class GetAccountsHub {

    private @Autowired RestUtils _restUtils;

    private final String GetAccountsUrl;

    private final JsonSerializer _jsonSerializer;

    private @Autowired Authentication _authentication;

    public GetAccountsHub(String getaccountsUrl) {

        GetAccountsUrl = getaccountsUrl;
        _jsonSerializer = JsonSerializer.DEFAULT_READABLE;

    }
    public ApiResponse<GetAccountsResponse> createOrGetAccountsHubRequest(GetAccountsRequest creategetAccountsRequest) throws ParseException, IOException, SerializeException {

        String AuthorizeToken= _authentication.getBearerToken();
       // System.out.println("Token is "+AuthorizeToken);
        //endpointUrl
        String endpointUrl = GetAccountsUrl;
       // System.out.println(endpointUrl);
        //Build a request
        String jsonRequest = _jsonSerializer.serialize(creategetAccountsRequest);
      //  System.out.println(jsonRequest);
        //Retrieve weather forecast
        Response responseBody = _restUtils.restApiResponse(
                endpointUrl,
                RequestType.POST,
                AuthorizeToken,
                jsonRequest,"AuthorizeToken");

        ResultLog.notice(
                "Request and Response for GetAccounts forecast",
                "URL "+endpointUrl,
                "Request "+
                        jsonRequest,
                "Response "+
                        responseBody.prettyPrint());


        //Response
        ApiResponse<GetAccountsResponse> response = ApiResponse.parseResponse(responseBody, GetAccountsResponse.class);
       // System.out.println("response is " + response);
        return response;

    }
}
