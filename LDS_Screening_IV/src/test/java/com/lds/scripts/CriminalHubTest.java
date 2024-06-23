package com.lds.scripts;

import com.rp.apis.apibuilder.CriminalHubRequestBuilder;
import com.rp.apis.functionallibrary.ApiResponse;
import com.rp.apis.functionallibrary.CriminalHub;
import com.rp.assertions.HardAssert;
import com.rp.base.ApiBase;
import com.lds.models.api.CriminalRequest;
import com.lds.models.api.CriminalResponse;
import io.qameta.allure.Description;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class CriminalHubTest extends ApiBase {

    private @Autowired CriminalHub _CriminalHub;
    private @Autowired CriminalHubRequestBuilder _Criminalbuilder;

    @Description("Verify Criminal Hub test")
    @Test(dataProvider = "SampleTestData")
    public void verifyCriminalHubTest(Map<String, String> data) throws ParseException, IOException, SerializeException {
        //Arrange
        CriminalRequest createCrimanaialRequest=_Criminalbuilder.criminalRequestBuilder(data);

        //act
        ApiResponse<CriminalResponse> crimRespone =_CriminalHub.createOrGetCriminalHubRequest(createCrimanaialRequest);
        System.out.println("Response is :");
        System.out.println(crimRespone.getRawResponse());

        HardAssert.assertEquals(200, crimRespone.getHttpStatusCode(),
                "Status Code",
                "Verify Payment status code");



    }

}
