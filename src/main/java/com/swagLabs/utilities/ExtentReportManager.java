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
import com.swagLabs.pojo.BaseClass;

public class ExtentReportManager extends BaseClass implements ITestListener 
{
	public  ExtentReports reports;
	public static ExtentTest test;

	public void onStart(ITestContext context) {
		reports = ExtentReport.createExtentReports();
	}

	public void onTestStart(ITestResult result) {

		System.out.println("************************************");
		System.out.println("************************************");
		System.out.println();
		System.out.println();
		System.out.println(result.getName() + " started executing.");
		
		test = reports.createTest(result.getTestClass().getName() +" - "+
				result.getMethod().getPriority() + ". " + result.getMethod().getMethodName());
		test.assignAuthor("Krushna Patare.");
		test.log(Status.INFO, result.getName() + " started executing.");
	}

	public void onTestSuccess(ITestResult result) 
	{	
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		System.out.println("Test is passed " + result.getName());
		test.log(Status.PASS, result.getName() + " got successfully executed");
		
		System.out.println(result.getName() + " got successfully executed");
		System.out.println();
		System.out.println();
		System.out.println("************************************");
		System.out.println("************************************");
		
	}
	

	public void onTestFailure(ITestResult result) 
	{
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		String screenshotDestinationPath = null;
		try {
			screenshotDestinationPath = BaseClass.screenshot.generateScreenshot(driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Test is failed " + result.getName());
		test.log(Status.INFO, result.getThrowable());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.addScreenCaptureFromPath(screenshotDestinationPath);
		
		System.out.println(result.getName() + " got failed");
		System.out.println();
		System.out.println();
		System.out.println("************************************");
		System.out.println("************************************");
	}

	public void onTestSkipped(ITestResult result) 
	{
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		System.out.println("Test Skipped " + result.getName());
		test.log(Status.INFO, result.getThrowable());
		test.log(Status.SKIP, result.getName() + " got skipped");
		System.out.println(result.getName() + " got skipped");
		System.out.println();
		System.out.println();
		System.out.println("************************************");
		System.out.println("************************************");
		
	}

	public void onFinish(ITestContext context) 
	{
		reports.flush();
	
		File extentReportCopy = new File(AutoConstant.extentReportPath);
		try {
			Desktop.getDesktop().browse(extentReportCopy.toURI());
			} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
	}
	
  
}
