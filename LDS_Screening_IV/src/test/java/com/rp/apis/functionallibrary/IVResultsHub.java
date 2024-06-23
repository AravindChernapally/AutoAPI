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

public class IVResultsHub {

    private @Autowired RestUtils _restUtils;

    private final String IVResultsUrl;

    private final JsonSerializer _jsonSerializer;

    private @Autowired Authentication _authentication;

    public IVResultsHub(String ivresultsUrl) {

        IVResultsUrl = ivresultsUrl;
        _jsonSerializer = JsonSerializer.DEFAULT_READABLE;

    }
    public ApiResponse<IVResultsResponse> createOrGetIVResultsHubRequest(IVResultsRequest createIVResultsRequest) throws ParseException, IOException, SerializeException {

        String AuthorizeToken= _authentication.getBearerToken();
         System.out.println("Token is "+AuthorizeToken);
        //endpointUrl
        String endpointUrl = IVResultsUrl;
        // System.out.println(endpointUrl);
        //Build a request
        String jsonRequest = _jsonSerializer.serialize(createIVResultsRequest);
//        String jsonRequest = "{\n" +
//                "   \"ConsumerId\": \"d4331ef0-95f0-4c5a-8916-6512ed3ee69e\",\n" +
//                "    \"TransactionId\": \"66b5baab-4aaf-46fb-81e6-4db079ddd5e5\",\n" +
//                "    \"OrderName\": \"IncomeVerification\",\n" +
//                "    \"CompanyID\": \"1048354\",\n" +
//                "    \"PropertyID\": \"4074161\",\n" +
//                "    \"PMS\": \"OS\"\n" +
//                "}";

        //System.out.println(jsonRequest);
        //Retrieve weather forecast
        Response responseBody = _restUtils.restApiResponse(
                endpointUrl,
                RequestType.POST,
                AuthorizeToken,
                jsonRequest,"AuthorizeToken");

        ResultLog.notice(
                " Request and Response for IVResults",
                "URL "+endpointUrl,
                "Request "
                        +jsonRequest,
                "Response "
                        +responseBody.prettyPrint());

        //Response
        ApiResponse<IVResultsResponse> response = ApiResponse.parseResponse(responseBody, IVResultsResponse.class);
        //System.out.println("response is " + response);
        return response;

    }
}
