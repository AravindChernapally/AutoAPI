package com.lds.scripts;

import com.rp.apis.apibuilder.GetAccountsRequestBuilder;
import com.rp.apis.functionallibrary.*;
import com.rp.assertions.HardAssert;
import com.rp.base.ApiBase;
import com.lds.models.api.*;
import io.qameta.allure.Description;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.Map;

public class GetAccountsTest extends ApiBase
{

    private @Autowired GetAccountsHub _GetAccountsHub;
    private @Autowired GetAccountsRequestBuilder _GetAccountsbuilder;

    @Description("Verify GetAccounts test")
    @Test(dataProvider = "GetAccountsTest")
    public void verifyGetAccountsTest(Map<String, String> data) throws ParseException, IOException, SerializeException, InterruptedException {

       // String ss=data.get("ConsumerId");


        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\nnamoji\\LDS_ScreeningApi_v2\\src\\test\\resources\\environment\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://codepen.io/team/yodlee-dev-ex/pen/zYrQrzJ");
        Thread.sleep(5000);

        int size = driver.findElements(By.tagName("iframe")).size();
       // System.out.println("no of frames"+ size);
        driver.switchTo().frame("result");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@id='token']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@id='token']")).sendKeys(IVVendorAccessTokenValue);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@id='url']")).click();
        driver.findElement(By.xpath("//input[@id='url']")).clear();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@id='url']")).sendKeys(IVVendorURLValue);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@id='btn-fastlink']")).click();
        Thread.sleep(10000);
        driver.switchTo().frame("fl-frame");//tfield-input has-text-weight-normal
        Thread.sleep(10000);
        driver.findElement(By.xpath("//input[@class='tfield-input has-text-weight-normal']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//input[@class='tfield-input has-text-weight-normal']")).sendKeys("IQ Bank");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@title='IQ Bank']")).click();
        Thread.sleep(10000);

        driver.findElement(By.xpath("//input[@class='tfield-input fieldId-172464']")).sendKeys("scteam_776625");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//input[@class='tfield-input fieldId-172465']")).sendKeys("scteam@iqb");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//input[@value='ENABLE ALL ACCOUNTS']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//button[@id='save-finish-btn']")).click();
        Thread.sleep(10000);
        driver.switchTo().frame("result");
        Thread.sleep(10000);
        String account1=driver.findElement(By.xpath("//span[@id='accountId']")).getText();
        Thread.sleep(10000);
        int h=Integer.parseInt(account1);
      //  System.out.println(h);
        int g=h+1;
        String account2=String.valueOf(g);
        String accounts=account1+","+account2;
      //  System.out.println(accounts);

        //Arrange
        GetAccountsRequest createGetAccountsRequest=_GetAccountsbuilder.GetAccountsRequestBuilder(data,accounts);
        //act
        ApiResponse<GetAccountsResponse> GetAccountsRespone =_GetAccountsHub.createOrGetAccountsHubRequest(createGetAccountsRequest);
//        System.out.println("Response is :");
//        System.out.println(GetAccountsRespone.getRawResponse());

        HardAssert.assertEquals(200, GetAccountsRespone.getHttpStatusCode(),
                "Status Code",
                "Verify GetAccounts code");
        String getaccountsResponsestring =GetAccountsRespone.getRawResponse();
       // System.out.println(getaccountsResponsestring);



    }


}
