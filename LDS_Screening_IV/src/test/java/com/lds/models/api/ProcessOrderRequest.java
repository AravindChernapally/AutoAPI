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
public class ProcessOrderRequest extends ApiResponse {
    private ConsumerInfo Consumer;
    private TransactionInfo Transaction;

}
