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

import com.swagLabs.base.BaseClass;
import com.swagLabs.utilities.ExtentReportManager;
import com.swagLabs.utilities.LogUtils;
import com.swagLabs.utilities.ReportUtils;

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class CheckoutOverviewPageTest extends BaseClass 
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
		    description = "<html><body><b><pre><span style='color:yellow;'>"
		    		+ "This test verifies that user can complete the order placing process."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyCheckoutFinishFunctionality(String firstname, String lastname, String postalCode, String result) throws InterruptedException, IOException 
	{
		LogUtils.info("********************************************************");
		LogUtils.info("***** <<<<< Starting verifyCheckoutFinishFunctionality >>>>> *****");
	
	    try {
	    	completeCheckoutOverview();
	    	String actualUrl = driver.getCurrentUrl();
			String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
	    	
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
	        LogUtils.info("***** Finished verifyCheckoutFinishFunctionality *****");
	        LogUtils.info("********************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }
	}

	
	public void completeCheckoutOverview() throws InterruptedException 
	{
		new CheckoutPageTest().checkOutComplete("Krushna", "Patare", "411016");
		SelUtils.scrollToElement(checkoutOverviewPage.getFinishButton(), driver);
		SelUtils.waitAndClick(checkoutOverviewPage.getFinishButton(), 2);

		LogUtils.info("Opened checkout success page.");
        ReportUtils.addScreenshot("Opened checkout success page.");
	}
	
	
}
