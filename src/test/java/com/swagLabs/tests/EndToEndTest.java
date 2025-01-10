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
import com.swagLabs.utilities.ExtentReportManager;
import com.swagLabs.utilities.LogUtils;
import com.swagLabs.utilities.PropertiesReader;
import com.swagLabs.utilities.ReportUtils;


@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class EndToEndTest extends BaseClass
{
	@Parameters({ "browser" }) 
	@BeforeMethod()
	public void initializeBrowser(@Optional("chrome") String browser) throws InterruptedException 
	{
		openBrowser(browser);
	}
	
	
	@AfterMethod()
	public void tearDown() 
	{	
		super.tearDown();
	}
	
	
	@Test(
		    priority = 1, 
		    description = "<html><body><b><pre><span style='color:yellow;'>"
		    		+ "This test verifies complete purchasing functionality cycle."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyEndToEndPurchasingFunctionality() throws InterruptedException, IOException 
	{
		LogUtils.info("**********************************************************************");
		LogUtils.info("***** <<<<< Starting verifyEndToEndPurchasingFunctionality >>>>> *****");
	
	    try 
	    {
	    	new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	    	new InventoryPageTest().addItemsToCart();
	    	new CartPageTest().checkout();
			new CheckoutPageTest().checkOutComplete("Krushna", "Patare", "411016");
	    	new CheckoutOverviewPageTest().completeCheckoutOverview();	    	
	    	selUtils.waitAndClick(checkoutCompletePage.getBackHomeButton(), 2);	
	    	LogUtils.info("Opened inventory page.");
	        ReportUtils.addScreenshot("Opened inventory page.");
	    	Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Page url should match.");   
	        new LoginPageTest().logOut();
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
	        LogUtils.info("***** Finished verifyEndToEndPurchasingFunctionality *****");
	        LogUtils.info("********************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }
	}

	
	
}











