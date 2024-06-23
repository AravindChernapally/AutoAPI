package com.rp.base;


import com.rp.reports.EnvironmentWriter;
import com.rp.reports.TestLogger;
import com.rp.util.JsonDataService;
import io.qameta.allure.Allure;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

@ContextConfiguration (locations = "classpath:spring-beans.xml")
public abstract class ApiBase extends AbstractTestNGSpringContextTests {
  public static String YodleeRequest;
    public static String ConsumerIdValue;
    public static String TransactionIdValue;
    public static String IVVendorURLValue;
    public static String IVVendorAccessTokenValue;

    @BeforeSuite
    public void setEnvironmentData(ITestContext testContext) {
        try {
            springTestContextPrepareTestInstance(); // Workaround for TestNG not binding the applicationContext in BeforeSuite.
            EnvironmentWriter environmentWriter = new EnvironmentWriter();
            environmentWriter.add("OS", System.getProperty("os.name"));
            environmentWriter.add("OS Version", System.getProperty("os.version"));
            environmentWriter.add("OS Arch", System.getProperty("os.arch"));
            environmentWriter.add("Java Version", System.getProperty("java.version"));
            environmentWriter.add("JDK", System.getProperty("java.vendor"));

            PropertySourcesPlaceholderConfigurer
                    propertySourcesPlaceholderConfigurer = (PropertySourcesPlaceholderConfigurer)applicationContext.getBean("propertySourcesPlaceholderConfigurer");
            if (propertySourcesPlaceholderConfigurer != null && propertySourcesPlaceholderConfigurer.getAppliedPropertySources().contains("localProperties")) {
                environmentWriter.add("Test Environment", propertySourcesPlaceholderConfigurer.getAppliedPropertySources().get("localProperties").getProperty("test_environment").toString());
            }

            environmentWriter.generateEnvironmentFile("target/allure-results", "environment.xml");
        } catch (Exception e) {
            System.out.println("Error while preparing environment file. Error is: " + e.getLocalizedMessage());
        }
    }

    @AfterMethod (description = "Test Report Results")
    public void afterMethod(ITestResult testResult) {
        String reportContent = TestLogger.buildReport(testResult);
        if (reportContent == null) {
            reportContent = "NO REPORT OUTPUT FROM TEST";
        }

        Allure.addAttachment("Test Execution Report", "text/html", reportContent, ".html");
    }

    @DataProvider (name = "ProcessOrder")
    public Iterator<Object[]> UppCustomerTestData(Method method) throws IOException {
        String rootFolder = (String)applicationContext.getBean("root_folder");
        return new JsonDataService().getDataFromJson(
                rootFolder,
                (String) applicationContext.getBean("ProcessOrder"),
                "ProcessOrder",
                method.getName());
    }

    @DataProvider (name = "YodleeAuthToken")
    public Iterator<Object[]> UppCustomerTestDataYodlee(Method method) throws IOException {
        String rootFolder = (String) applicationContext.getBean("root_folder");
        return new JsonDataService().getDataFromJson(
                rootFolder,
                (String) applicationContext.getBean("YodleeAuthToken"),
                "YodleeAuthToken",
                method.getName());
    }

    @DataProvider (name = "GetAccountsTest")
    public Iterator<Object[]> UppCustomerTestGetAccount(Method method) throws IOException {
        String rootFolder = (String) applicationContext.getBean("root_folder");
        return new JsonDataService().getDataFromJson(
                rootFolder,
                (String) applicationContext.getBean("GetAccountsTest"),
                "GetAccountsTest",
                method.getName());
    }

    @DataProvider (name = "IVResultsTest")
    public Iterator<Object[]> UppCustomerTestIVResults(Method method) throws IOException {
        String rootFolder = (String) applicationContext.getBean("root_folder");
        return new JsonDataService().getDataFromJson(
                rootFolder,
                (String) applicationContext.getBean("IVResultsTest"),
                "IVResultsTest",
                method.getName());
    }


}
