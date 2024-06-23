package com.rp.apis.apiconfig;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Authentication {

    public String getBearerToken() {

        String authBaseUrl = "https://www-qa.realpage.com/login/identity/connect/token";
        String grantType="client_credentials";
        String clientId = "onlineleasing-server";
        String clientSecret = "F7F4a421-3A9d-4EB8-9E13-dF6D0C84#1fF";
        String scope = "screening-service-iv crr-svc-api";

        Response response = given()
                .param("grant_type", "client_credentials")
                .param("client_id", clientId)
                .param("client_secret", clientSecret)
                .param("scope", scope)
                .when()
                .post(authBaseUrl)
                .then()
                .extract()
                .response();
        if (response.statusCode() == 200) {
            ExtractableResponse<Response> jsonResp = response.then().contentType(ContentType.JSON).extract();
            return jsonResp.path("access_token").toString();
        } else {
            System.out.println("OAuth Authorization Response Code: " + response.statusCode());
            return null;
        }

    }
}
