package com.rp.apis.apibuilder;

import com.rp.base.ApiBase;
import com.lds.models.api.*;

import java.util.Map;

public class IVResultsRequestBuilder {

    public IVResultsRequest IVResultsRequestBuilder(Map<String, String> data){

        String consumerId = ApiBase.ConsumerIdValue;
        String transactionId = ApiBase.TransactionIdValue;

        String orderName = data.get("OrderName");
        String companyID = data.get("CompanyID");
        String propertyID = data.get("PropertyID");
        String pMS = data.get("PMS");



        IVResultsRequest ivresultsRequest= IVResultsRequest.builder()
                .ConsumerId(consumerId)
                .TransactionId(transactionId)
                .OrderName(orderName)
                .CompanyID(companyID)
                .PropertyID(propertyID)
                .PMS(pMS)
                .build();

        return ivresultsRequest;

    }



}
