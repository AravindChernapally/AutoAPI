package com.rp.util;


import com.rp.apis.apiresources.RequestType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestUtils {

    /***
     * This method will create request with Bearer Token and return the Http response
     * @param url
     * @param requestType
     * @param accessToken
     * @param request
     * @return
     */

    public Response restApiResponse(String url, RequestType requestType, String accessToken, String request,String tokenType) {

        Response response;
        RequestSpecification requestSpec = given().relaxedHTTPSValidation().
                contentType("application/json;charset=UTF-8");

        if (accessToken != null && tokenType.equalsIgnoreCase("Bearer")) {
            requestSpec.header("Authorization", "Bearer " + accessToken);

        }else  if (accessToken != null && tokenType.equalsIgnoreCase("AuthorizeToken"))
        {
            requestSpec.header("AuthorizeToken",accessToken);

        }
        if (request != null){
            requestSpec.contentType("application/json");
        }
        response = switch (requestType) {
            case GET -> requestSpec.when().get(url);
            case POST -> requestSpec.body(request).when().post(url);
            case PUT -> requestSpec.body(request).when().put(url);
            case PATCH -> requestSpec.body(request).when().patch(url);
            case DELETE -> requestSpec.when().delete(url);
            default -> null;
        };
        if (response != null) {
            response = response.then()
                    .extract()
                    .response();
        }
        return response;
    }
}
