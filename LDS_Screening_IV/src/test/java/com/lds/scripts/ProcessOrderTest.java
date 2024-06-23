package com.lds.scripts;

import com.rp.apis.apibuilder.ProcessOrderHubRequestBuilder;
import com.rp.apis.functionallibrary.ApiResponse;
import com.rp.apis.functionallibrary.ProcessOrderHub;
import com.rp.assertions.HardAssert;
import com.rp.base.ApiBase;
import com.lds.models.api.*;
import io.qameta.allure.Description;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.Map;

public class ProcessOrderTest extends ApiBase
{

    private @Autowired ProcessOrderHub _ProcessOrderHub;
    private @Autowired ProcessOrderHubRequestBuilder _ProcessOrderbuilder;

    @Description("Verify Process Order test case 1")
    @Test(dataProvider = "ProcessOrder")
    public void verifyConsumerIdTransactionId(Map<String, String> data) throws ParseException, IOException, SerializeException
    {

      //  logger.info("Process Order");

        //Arrange
        ProcessOrderRequest createProcessOrderRequest=_ProcessOrderbuilder.ProcessOrderRequestBuilder(data);
        //act
        ApiResponse<ProcessOrderResponse> ProcessRespone =_ProcessOrderHub.createOrGetProcessOrderHubRequest(createProcessOrderRequest);

//        JsonPath j = new JsonPath(ProcessRespone.getRawResponse());
//         String ConsumerIdvalue= j.getString("ConsumerId");
//        System.out.println(ConsumerIdvalue);
//        String TransactionIdvalue= j.getString("TransactionId");
//        System.out.println(TransactionIdvalue);

        // int s = j.getInt("Orders.size()");

        HardAssert.assertEquals(200, ProcessRespone.getHttpStatusCode(),
                "Status Code",
                "Verify Process Order status code");


         YodleeRequest=ProcessRespone.getRawResponse();

    }


    @Description("Verify Process Order test Case2")
    @Test(dataProvider = "ProcessOrder")
    public void verifyConsumerIdTransactionId2(Map<String, String> data) throws ParseException, IOException, SerializeException
    {

        //  logger.info("Process Order");

        //Arrange
        ProcessOrderRequest createProcessOrderRequest=_ProcessOrderbuilder.ProcessOrderRequestBuilder(data);
        //act
        ApiResponse<ProcessOrderResponse> ProcessRespone =_ProcessOrderHub.createOrGetProcessOrderHubRequest(createProcessOrderRequest);

//        JsonPath j = new JsonPath(ProcessRespone.getRawResponse());
//         String ConsumerIdvalue= j.getString("ConsumerId");
//        System.out.println(ConsumerIdvalue);
//        String TransactionIdvalue= j.getString("TransactionId");
//        System.out.println(TransactionIdvalue);

        // int s = j.getInt("Orders.size()");

        HardAssert.assertEquals(200, ProcessRespone.getHttpStatusCode(),
                "Status Code",
                "Verify Process Order status code");




    }

}
