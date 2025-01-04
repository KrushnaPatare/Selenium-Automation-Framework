package com.swagLabs.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swagLabs.base.BaseClass;
import com.swagLabs.utilities.DataProviders;
import com.swagLabs.utilities.ExtentReportManager;
import com.swagLabs.utilities.LogUtils;
import com.swagLabs.utilities.PropertiesReader;
import com.swagLabs.utilities.ReportUtils;

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class CheckoutPageTest extends BaseClass 
{
	@Parameters({ "browser" }) 
	@BeforeMethod()
	public void initializeBrowser(@Optional("chrome") String browser) throws InterruptedException 
	{
		openBrowser(browser);
	}
	
	
	@AfterMethod()
	public void tearDown() throws IOException, InterruptedException 
	{	
		driver.quit();
	}
	
	
	@Test(
		    priority = 1, 
    		dataProvider = "checkoutData", 
		    dataProviderClass = DataProviders.class,
		    description = "<html><body><b><pre><span style='color:yellow;'>"
		    		+ "This test verifies that user can complete the order placing process."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyCheckoutFunctionality(String firstname, String lastname, String postalCode, String result) throws InterruptedException, IOException 
	{
		LogUtils.info("********************************************************");
		LogUtils.info("***** <<<<< Starting verifyCheckoutFunctionality >>>>> *****");
	
	    try {
	    	new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	    	new InventoryPageTest().addItemsToCart();
	    	new CartPageTest().checkout();
	    	checkOutComplete(firstname, lastname, postalCode);
	        
	    	String actualUrl = driver.getCurrentUrl();
			String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
	        
			new LoginPageTest().logOut();

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
	        LogUtils.error("Test failed due to assertion error: " + e.getMessage());
	        LogUtils.logException(e);
	        ExtentReportManager.test.fail("Test failed due to assertion error: " + e.getMessage());
	        Assert.fail("Assertion error occurred: " + e.getMessage());
	        throw e;
	    } 
	    catch (Exception e) 
	    {
	        LogUtils.error("Unexpected exception occurred: " + e.getMessage());
	        LogUtils.logException(e);
	        ExtentReportManager.test.fail("Unexpected exception: " + e.getMessage());
	        throw e;
	    } 
	    finally 
	    {
	        LogUtils.info("***** Finished verifyCheckoutFunctionality *****");
	        LogUtils.info("********************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }
		
	}
	
	

	
	
	public void checkOutComplete(String firstname, String lastname, String postalCode) throws InterruptedException 
	{
        SelUtils.sendKeysMethod(checkoutPage.getFirstNameField(), firstname, 2);
        SelUtils.sendKeysMethod(checkoutPage.getLastNameField(), lastname, 2);
        SelUtils.sendKeysMethod(checkoutPage.getPostalCodeField(), postalCode,2);

        SelUtils.waitTime(3);
        
        LogUtils.info("Entered firstname, lastname, postalCode.");
        ReportUtils.addScreenshot("Entered firstname, lastname, postalCode.");
        
        SelUtils.waitTime(3);
        
        SelUtils.waitAndClick(checkoutPage.getContinueButton(), 3);
        LogUtils.info("Opened checkout complete page.");
        ReportUtils.addScreenshot("Opened checkout complete page.");
	}
}
