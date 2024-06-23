package com.lds.models.db;

import lombok.Getter;
import lombok.Setter;

    /*
    Entities for resident details queries
     */

@Getter
@Setter
public class ResidentDetails {

    public Long residentId;
    public String firstName;
    public String lastName;
    public String unit;
    public String address;
    public Boolean isActive;

}
