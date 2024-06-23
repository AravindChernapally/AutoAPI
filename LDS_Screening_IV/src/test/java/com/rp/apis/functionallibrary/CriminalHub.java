package com.rp.apis.functionallibrary;


import com.rp.apis.apiconfig.Authentication;
import com.rp.apis.apiresources.RequestType;
import com.lds.models.api.CriminalRequest;
import com.lds.models.api.CriminalResponse;
import com.rp.reports.ResultLog;
import com.rp.util.RestUtils;
import io.restassured.response.Response;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class CriminalHub {

    private @Autowired RestUtils _restUtils;

    private final String _baseUrlCrim;

    private final JsonSerializer _jsonSerializer;

    private @Autowired Authentication _authentication;

    public CriminalHub(String apiCrimUrl) {

        _baseUrlCrim = apiCrimUrl;
        _jsonSerializer = JsonSerializer.DEFAULT_READABLE;

    }
    public ApiResponse<CriminalResponse> createOrGetCriminalHubRequest(CriminalRequest createCrimanaialRequest) throws ParseException, IOException, SerializeException {

        //endpointUrl
        String endpointUrl = _baseUrlCrim + "/CriminalRecords";

        //Build a request
        String jsonRequest = _jsonSerializer.serialize(createCrimanaialRequest);

        //Retrieve weather forecast
        Response responseBody = _restUtils.restApiResponse(
                endpointUrl,
                RequestType.POST,
                null,
                jsonRequest,"Bearer");

        ResultLog.notice(
                "Request and Response for weather forecast",
                endpointUrl,
                "",
                responseBody.prettyPrint());

        //Response
        ApiResponse<CriminalResponse> response = ApiResponse.parseResponse(responseBody, CriminalResponse.class);

        return response;

    }
}
