package com.swagLabs.tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.swagLabs.base.BaseClass;
import com.swagLabs.utilities.DataProviders;
import com.swagLabs.utilities.ExceptionHandler;
import com.swagLabs.utilities.ExtentReportManager;
import com.swagLabs.utilities.LogUtils;
import com.swagLabs.utilities.ReportUtils;
import com.swagLabs.utilities.Validator;


@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class LoginPageTest extends BaseClass 
{  	 
	@Parameters({ "browser" }) 
	@BeforeMethod()
    public void initializeBrowser(@Optional("chrome") String browser) throws InterruptedException 
	{
        openBrowser(browser); // Call the openBrowser method from BaseClass
    }

    @AfterMethod()
    public void tearDown() 
    {
        super.tearDown(); // Call the tearDown method from BaseClass to handle cleanup
    }
	

	@Test(
		    priority = 1, 
		    dataProvider = "loginData", 
		    dataProviderClass = DataProviders.class, 
		    description = "<html><body><b><pre><span style='color:yellow;'>"
		    			+ "This test verifies the login functionality with testdata provided."
		    			+ "</span></pre></b></body></html>"
		 )
	public void checkLoginFunctionality
	(
		String username, 
		String password,
		String result
	)throws InterruptedException, IOException 
	{
		LogUtils.info("********************************************************");
		LogUtils.info("****** <<<<<Starting checkLoginFunctionality>>>>> ******");

		 try 
		 {
            logIn(username, password); // Perform login action

            Validator.verifyUrl(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", result);
	     } 
		 catch (AssertionError e) 
		 {
			 ExceptionHandler.handleAssertionError(e, "Assertion failure during test execution.");
	     } 
		 catch (Exception e)
		 {
			 ExceptionHandler.handleException(e, "Unexpected error during test execution.");
	     } 
		 finally 
		 {
            LogUtils.info("***** Finished checkLoginFunctionality *****");
	        LogUtils.info("*********************************************");
            ExtentReportManager.getTest().info("Test finished.");
	     } 
    }
	
	
	public void logIn(String username, String password) throws InterruptedException 
	{
		LogUtils.info("Opened login page.");
		ReportUtils.addScreenshot(Status.INFO,"Opened login page.");
		
//		System.out.println(username);
//		System.out.println(password);
		
		selUtils.sendKeysMethod(loginPage.getUsernameField(), username, 1);
		selUtils.sendKeysMethod(loginPage.getPasswordField(), password, 1);
		
		LogUtils.info("Entered username and password.");
		ReportUtils.addScreenshot(Status.INFO, "Entered username and password.");

		selUtils.waitForElementAndClick(loginPage.getLoginButton(), 3) ;
		selUtils.waitForSeconds(3);

		LogUtils.info("Logged in Successfully!");
		ReportUtils.addScreenshot(Status.INFO,"Logged in Successfully!");	
	}

	
	public void logOut() throws InterruptedException 
	{
		selUtils.waitForElementAndClick(basePage.getBurgerMenuButton(), 2);       
		selUtils.waitForSeconds(2);
		selUtils.waitForElementAndClick(basePage.getLogoutLink(), 2);
		selUtils.waitForSeconds(2);
		LogUtils.info("Logged out Successfully!");
		ReportUtils.addScreenshot(Status.INFO,"Logged out Successfully!");
	}
	

}













