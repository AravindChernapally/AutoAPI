package com.lds.scripts;

import com.rp.apis.apibuilder.IVResultsRequestBuilder;
import com.rp.apis.functionallibrary.*;
import com.rp.assertions.HardAssert;
import com.rp.base.ApiBase;
import com.lds.models.api.*;
import io.qameta.allure.Description;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class IVResultsTest extends ApiBase
{

    private @Autowired IVResultsHub _IVResultsHub;
    private @Autowired IVResultsRequestBuilder _IVResultsbuilder;

    @Description("Verify IVResults test")
    @Test(dataProvider = "IVResultsTest")
    public void verifyIVResultsTest(Map<String, String> data) throws ParseException, IOException, SerializeException
    {



        //Arrange
        IVResultsRequest createIVResultsRequest=_IVResultsbuilder.IVResultsRequestBuilder(data);
        //act
        ApiResponse<IVResultsResponse> IVResultsRespone =_IVResultsHub.createOrGetIVResultsHubRequest(createIVResultsRequest);

//        JsonPath j = new JsonPath(ProcessRespone.getRawResponse());
//         String ConsumerIdvalue= j.getString("ConsumerId");
//        System.out.println(ConsumerIdvalue);
//        String TransactionIdvalue= j.getString("TransactionId");
//        System.out.println(TransactionIdvalue);

        // int s = j.getInt("Orders.size()");

        HardAssert.assertEquals(200, IVResultsRespone.getHttpStatusCode(),
                "Status Code",
                "Verify IVResults status code");


        // YodleeRequest=IVResultsRespone.getRawResponse();


        //JsonPath j = new JsonPath(IVResultsRespone.getRawResponse());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readValue(IVResultsRespone.getRawResponse(), JsonNode.class);
        JsonNode nameNode = node.get("IncomeDetails").get("AllBanksTotalDeposit");
        JsonNode nameNode1 = node.get("IncomeDetails").get("VerifiedAverageMonthlyIncome");
        String AllBanksTotalDeposit = nameNode.asText();
        String VerifiedAverageMonthlyIncome = nameNode1.asText();
        System.out.println("AllBanksTotalDeposit "+ AllBanksTotalDeposit);
        System.out.println("VerifiedAverageMonthlyIncome "+ VerifiedAverageMonthlyIncome);
        float h=Float.parseFloat(AllBanksTotalDeposit);
        float p=Float.parseFloat(VerifiedAverageMonthlyIncome);

        float a=h/3;
        if(a==p)
        {
            System.out.println("VerifiedAverageMonthlyIncome  "+  VerifiedAverageMonthlyIncome
                    +" is Three times of AllBanksTotalDeposit  "+ AllBanksTotalDeposit);
        }else {

            System.out.println("VerifiedAverageMonthlyIncome"+ VerifiedAverageMonthlyIncome
                    +" is not Three times of AllBanksTotalDeposit"+ AllBanksTotalDeposit);
        }

        JsonNode array = node.get("IncomeDetails").get("FinancialDetails");
        Iterator<JsonNode> H = array.get("AccountDetails").elements();
        ArrayList<String> totaldisposits=new ArrayList<>();

        while (H.hasNext())
        {
             JsonNode G=H.next();
             String TotalDeposit=G.get("TotalDeposit").toString();
             totaldisposits.add(TotalDeposit);
        }

        float AllBanksTotalDepositValue=Float.parseFloat(AllBanksTotalDeposit);
        float TotalDepositBank1=Float.parseFloat(totaldisposits.get(0));
        float TotalDepositBank2=Float.parseFloat(totaldisposits.get(1));
        if(TotalDepositBank1+TotalDepositBank2==AllBanksTotalDepositValue)
        {
            System.out.println("AllBanksTotalDepositValue  "+ AllBanksTotalDepositValue +
                    " is equal to total deposits of banks "+ TotalDepositBank1 + " and "+ TotalDepositBank2);
        }else {
            System.out.println("AllBanksTotalDepositValue  "+ AllBanksTotalDepositValue +
                    " is not equal total deposits of banks "+ TotalDepositBank1+ " and "+ TotalDepositBank2);
        }


        Iterator<JsonNode> D = array.get("AccountTransactionDetails").elements();

        ArrayList<String> TotalAmount=new ArrayList<>();
        while (D.hasNext())
        {
            JsonNode K=D.next();

            String AccountID=K.get("AccountId").toString();
            String Amount=K.get("Amount").toString();
           // System.out.println("AccountId : "+AccountID+" Amount  "+ Amount );
            TotalAmount.add(Amount);
        }
        float total=0;
        for(int i=0;i<TotalAmount.size();i++)
        {
            total=total+Float.parseFloat(TotalAmount.get(i).toString());
            if(total==AllBanksTotalDepositValue)
            {
                System.out.println("All amount of two banks "+ total+
                        " is equal to AllBanksTotalDepositValue  "+ AllBanksTotalDepositValue );
            }
        }





//        String TotalDeposit = jsonNode.asText();
//        System.out.println(TotalDeposit);

//        String AllBanksTotalDeposit= j.getString("AllBanksTotalDeposit");
//        System.out.println(AllBanksTotalDeposit);
//        String TotalDepositvalue= j.getString("TotalDeposit");
//        System.out.println(TotalDepositvalue);

    }


}
