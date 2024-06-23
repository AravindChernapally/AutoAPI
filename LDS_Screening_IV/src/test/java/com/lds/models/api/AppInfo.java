package com.lds.models.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class AppInfo {

    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String dob;
    private String gender;
    private List<AppInfo> creditInfo;

}
