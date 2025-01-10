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
public class ProductPageTest extends BaseClass 
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
		    		+ "This test verifies that user is able to add product to cart from product page."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyUserIsAbleToAddProductToCart() throws InterruptedException, IOException 
	{
	    LogUtils.info("*****************************************************************************");
	    LogUtils.info("********* <<<<< Starting verifyUserIsAbleToAddProductToCart >>>>> ***********");

	    try 
	    {
	        new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	        selUtils.waitAndClick(inventoryPage.getFirstProduct(), 2);
	        
	        LogUtils.info("Opened product page.");
	        ReportUtils.addScreenshot("Opened product page.");
	        
	        selUtils.waitAndClick(productPage.getAddToCartButton(), 2);
  
	        LogUtils.info("Added product to cart.");
	        ReportUtils.addScreenshot("Added product to cart.");
	        
	        selUtils.openCustomTab("https://www.saucedemo.com/cart.html");
	        
	        selUtils.waitTime(2);
	        selUtils.switchToWindowByUrlBase("https://www.saucedemo.com/cart.html");
	        selUtils.waitTime(2);
	        
	        LogUtils.info("Opened cart page.");
	        ReportUtils.addScreenshot("Opened cart page.");

	        Assert.assertTrue(cartPage.getCartItemDetails().getText().toLowerCase().contains(inventoryPage.getFirstProduct().getText().toLowerCase()),
	        		"Cart should contain added product name.");
	        
	        selUtils.switchToWindowByUrlBase("https://www.saucedemo.com/inventory-item.html?id=2");
	        selUtils.waitTime(2);
	        selUtils.closeOtherWindows();
	        
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
	        LogUtils.info("******* Finished verifyUserIsAbleToAddProductToCart *******");
	        LogUtils.info("***********************************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }
	}


}















