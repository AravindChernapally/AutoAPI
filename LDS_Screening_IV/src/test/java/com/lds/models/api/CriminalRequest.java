package com.lds.models.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rp.apis.functionallibrary.ApiResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CriminalRequest extends ApiResponse {
    private AppInfo appinfo;
    private List<StateFilters> statefilters;
    private Settings settings;
    private  String referenceId;
    private  String client;

}
