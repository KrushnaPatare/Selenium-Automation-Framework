package com.swagLabs.tests;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.swagLabs.base.BaseClass;
import com.swagLabs.utilities.DataProviders;
import com.swagLabs.utilities.ExtentReportManager;
import com.swagLabs.utilities.LogUtils;
import com.swagLabs.utilities.ReportUtils;


@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class LoginPageTest extends BaseClass 
{  	
	@Parameters({ "browser" }) 
	@BeforeMethod()
	public void initializeBrowser(@Optional("chrome") String browser) throws InterruptedException 
	{
		openBrowser(browser);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@AfterMethod()
	public void tearDown() throws IOException, InterruptedException 
	{	
		driver.quit();
	}
	

	@Test(
		    priority = 1, 
		    dataProvider = "loginData", 
		    dataProviderClass = DataProviders.class, 
		    description = "<html><body><b><pre><span style='color:yellow;'>This test verifies the login functionality with testdata provided.</span></pre></b></body></html>"
		)
	public void checkLoginFunctionality(
										String username, 
										String password,
										String result
										)
			throws InterruptedException, IOException 
	{
		LogUtils.info("********************************************************");
		LogUtils.info("***** <<<<<Starting checkLoginFunctionality>>>>> *****");

        try {
        LogUtils.info("Test started with parameters - Username: {"+username+"}, Password: {"+password+"}");
        ReportUtils.logMessage(Status.INFO,"<p><b>Parameters of testcase:<br>"
				+ "username = "+ username +"<br>"
				+"password = "+ password +"<br>"
				+"password = "+ result +"<br>"
				+"</b>"+"</p>");
		

        logIn(username, password);

		String actualUrl = BaseClass.driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		
		switch(result.toLowerCase()) 
    	{
    		case "pass":
				LogUtils.info("For 'pass' condition " +actualUrl+ " " +expectedUrl);
	    		Assert.assertEquals(actualUrl, expectedUrl, "Page Url should match.");
	    		break;
    		case "fail":
				LogUtils.info("For 'fail' condition " +actualUrl+ " " +expectedUrl);
	    		Assert.assertNotEquals(actualUrl, expectedUrl, "Page Url should not match.");
	    		break;
    		default:
	    		throw new IllegalArgumentException("Wrong argument is entered. Please enter 'Pass' or 'Fail' as argument.");
    	}	
		
        }
        catch (AssertionError e) 
	    {
        	LogUtils.error("Test failed due to exception: " + e.getMessage());
        	LogUtils.debug("Exception details" + e);
	        ExtentReportManager.test.fail("Test failed due to exception: " + e.getMessage());
	        Assert.fail();
	        throw e;
	    } 
	    catch (Exception e) 
	    {
	    	LogUtils.error("Unexpected exception occurred: {}" + e.getMessage());
	    	LogUtils.debug("Exception details" + e);
	        ExtentReportManager.test.fail("Unexpected exception: " + e.getMessage());
	        throw e;
	    } 
	    finally 
	    {
	    	LogUtils.info("***** Finished checkLoginFunctionality *****");
	    	LogUtils.info("********************************************");
	        ExtentReportManager.test.info("Test finished");
	    }    
    }
	
	
	public void logIn(String username, String password) throws InterruptedException 
	{
		LogUtils.info("Opened login page.");
		ReportUtils.addScreenshot(Status.INFO,"Opened login page.");
		
		SelUtils.sendKeysMethod(loginPage.getUsernameField(), username, 1);
		SelUtils.sendKeysMethod(loginPage.getPasswordField(), password, 1);
		
		LogUtils.info("Entered username and password.");
		ReportUtils.addScreenshot(Status.INFO, "Entered username and password.");

		SelUtils.waitAndClick(loginPage.getLoginButton(), 3) ;
		
		LogUtils.info("Logged in Successfully!");
		SelUtils.waitTime(3);
		
		ReportUtils.addScreenshot(Status.INFO,"Logged in Successfully!");	
	}



}


