package com.rp.apis.apibuilder;

import com.rp.base.ApiBase;
import com.lds.models.api.*;

import java.util.Map;

public class GetAccountsRequestBuilder {

    public GetAccountsRequest GetAccountsRequestBuilder(Map<String, String> data,String account){
        String consumerId = ApiBase.ConsumerIdValue;
        String transactionId = ApiBase.TransactionIdValue;
        String authToken = ApiBase.IVVendorAccessTokenValue;
        String accountIds = account;

        YodleeFinancialAccountRequestInfo yodleeFinancialAccountRequest=new YodleeFinancialAccountRequestInfo();
        yodleeFinancialAccountRequest.setAuthToken(authToken);
        yodleeFinancialAccountRequest.setAccountIds(accountIds);

        GetAccountsRequest getaccountsRequest= GetAccountsRequest.builder()
                .ConsumerId(consumerId)
                .TransactionId(transactionId)
                .YodleeFinancialAccountRequest(yodleeFinancialAccountRequest)
                .build();

        return getaccountsRequest;

    }



}
