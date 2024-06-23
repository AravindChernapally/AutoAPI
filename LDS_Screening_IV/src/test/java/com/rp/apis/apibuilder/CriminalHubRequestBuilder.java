package com.rp.apis.apibuilder;

import com.lds.models.api.AppInfo;
import com.lds.models.api.CriminalRequest;
import com.lds.models.api.Settings;
import com.lds.models.api.StateFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CriminalHubRequestBuilder {

    public CriminalRequest criminalRequestBuilder(Map<String, String> data){
        String firstName = data.get("firstName");
        String middleName = data.get("middleName");
        String lastName = data.get("lastName");
        String suffix = data.get("suffix");
        String dob = data.get("dob");
        String gender = data.get("gender");
        String state = data.get("state");
        int nationalSOR = Integer.parseInt( data.get("nationalSOR"));
        int noDOB = Integer.parseInt( data.get("noDOB"));
        int classification =Integer.parseInt( data.get("classification"));
        String vendor = data.get("vendor");

        AppInfo appInfo = new AppInfo();
        appInfo.setFirstName(firstName);
        appInfo.setFirstName(middleName);
        appInfo.setLastName(lastName);
        appInfo.setSuffix(suffix);
        appInfo.setDob(dob);
        appInfo.setGender(gender);


        AppInfo creditInfo_AppInfo = new AppInfo();

        creditInfo_AppInfo.setFirstName("String");
        creditInfo_AppInfo.setMiddleName("String");
        creditInfo_AppInfo.setLastName("String");
        creditInfo_AppInfo.setSuffix("String");

        List<AppInfo> creditInfo_AppInfoList=new ArrayList<>();
        creditInfo_AppInfoList.add(creditInfo_AppInfo);
        appInfo.setCreditInfo(creditInfo_AppInfoList);


        StateFilters stateFilters = new StateFilters();
        stateFilters.setState(state);

        List<StateFilters> stateFiltersList=new ArrayList<>();
        stateFiltersList.add(stateFilters);

        Settings settings = new Settings();
        settings.setNationalSOR(nationalSOR);
        settings.setNoDOB(noDOB);
        settings.setClassification(classification);
        settings.setVendor(vendor);


        CriminalRequest criminalRequest= CriminalRequest.builder()
                .appinfo(appInfo)
                .referenceId("String")
                .client("String")
                .settings(settings)
                .statefilters(stateFiltersList)
                .build();

    return criminalRequest;

    }


//Test
}
