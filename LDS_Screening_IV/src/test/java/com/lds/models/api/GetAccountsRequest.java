package com.lds.models.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rp.apis.functionallibrary.ApiResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAccountsRequest extends ApiResponse {

    private  String ConsumerId;
    private  String TransactionId;
    private YodleeFinancialAccountRequestInfo YodleeFinancialAccountRequest;


    //private TransactionInfo Transaction;

}
