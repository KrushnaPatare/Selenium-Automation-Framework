package com.swagLabs.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.swagLabs.base.BaseClass;

public class ExtentReportManager extends BaseClass implements ITestListener 
{
	private ExtentReports reports;
	public static ExtentTest test;

    public void onStart(ITestContext context) 
    {
        reports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(TestContext.extentReportPath);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("SwagLabs Test Automation Results Report");
        sparkReporter.config().setDocumentTitle("SwagLabs Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");

        reports.attachReporter(sparkReporter);

        reports.setSystemInfo("Application URL", PropertiesReader.getProperty("baseUrl"));
        reports.setSystemInfo("Username", System.getProperty("user.name"));
        reports.setSystemInfo("Operating System", System.getProperty("os.name"));
//        reports.setSystemInfo("OS Version", System.getProperty("os.version"));
//        reports.setSystemInfo("OS Architecture", System.getProperty("os.arch"));
        reports.setSystemInfo("Java Version", System.getProperty("java.version"));

        System.out.println("ExtentReportManager initialized.........!!!");
    }

    
    public void onTestStart(ITestResult result) 
    {
        System.out.println("************************************");
        System.out.println(result.getName() + " started executing.");

         test = reports.createTest
		 (
            result.getTestClass().getName() + " - " +
            result.getMethod().getPriority() + ". " +
            result.getMethod().getMethodName(),
            result.getMethod().getDescription()
         );


        test.assignAuthor("Krushna Patare.");
        test.log(Status.INFO, result.getName() + " started executing.");        
    }

    public void onTestSuccess(ITestResult result) 
    {
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");

        System.out.println(result.getName() + " got successfully executed");
        System.out.println("************************************");
    }

    
    public void onTestFailure(ITestResult result) 
    {
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test failed due to: " + result.getThrowable().getMessage());
        test.log(Status.FAIL, result.getName() + " got failed");
        ReportUtils.addScreenshot("*****");

        System.out.println(result.getName() + " got failed");
        System.out.println("************************************");
    }

    
    public void onTestSkipped(ITestResult result) 
    {
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.INFO, result.getThrowable());
        test.log(Status.SKIP, result.getName() + " got skipped");

        System.out.println(result.getName() + " got skipped");
        System.out.println("************************************");
    }

    
    public void onFinish(ITestContext context) 
    {
        reports.setSystemInfo("Browser Name", browserName);
        reports.setSystemInfo("Browser Version", browserVersion);
        reports.flush();

        // Try to open the report
        File extentReportCopy = new File(TestContext.extentReportPath);
        try 
        {
            Desktop.getDesktop().browse(extentReportCopy.toURI());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    
    public static ExtentTest getTest() 
    {
        return test;
    }
    
}























