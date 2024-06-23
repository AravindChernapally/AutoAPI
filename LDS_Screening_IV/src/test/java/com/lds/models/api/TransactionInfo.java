package com.lds.models.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TransactionInfo {

    private String CompanyID;
    private String PropertyID;
    private String PMS;
    private String AppPartner;
    private String Name;
    private List<OrderInfo> Orders;

}
