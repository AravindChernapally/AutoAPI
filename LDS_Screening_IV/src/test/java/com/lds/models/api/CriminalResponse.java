package com.lds.models.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rp.apis.functionallibrary.ApiResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CriminalResponse extends ApiResponse {
    private String status;

}
