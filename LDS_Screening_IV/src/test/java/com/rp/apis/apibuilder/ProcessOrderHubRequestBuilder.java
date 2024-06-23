package com.rp.apis.apibuilder;

import com.lds.models.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessOrderHubRequestBuilder {

    public ProcessOrderRequest ProcessOrderRequestBuilder(Map<String, String> data){
        String FirstName = data.get("FirstName");
        String LastName = data.get("LastName");
        String EmailAddress = data.get("EmailAddress");
        String AppPartnerUniqueId = data.get("AppPartnerUniqueId");


        String CompanyID = data.get("CompanyID");
        String PropertyID = data.get("PropertyID");
        String PMS = data.get("PMS");
        String AppPartner = data.get("AppPartner");
        String Name = data.get("Name");


        ConsumerInfo consumer = new ConsumerInfo();
        consumer.setFirstName(FirstName);
        consumer.setLastName(LastName);
        consumer.setEmailAddress(EmailAddress);
        consumer.setAppPartnerUniqueId(AppPartnerUniqueId);


        TransactionInfo transaction =new TransactionInfo();
        transaction.setCompanyID(CompanyID);
        transaction.setPropertyID(PropertyID);
        transaction.setPMS(PMS);
        transaction.setAppPartner(AppPartner);

        OrderInfo orders =new OrderInfo();
        orders.setName(Name);

        List<OrderInfo> ordersInfo_transactionList=new ArrayList<>();
        ordersInfo_transactionList.add(orders);
        transaction.setOrders(ordersInfo_transactionList);

        ProcessOrderRequest processOrderRequest= ProcessOrderRequest.builder()
                .Consumer(consumer)
                .Transaction(transaction)
                .build();

        return processOrderRequest;

    }



}
