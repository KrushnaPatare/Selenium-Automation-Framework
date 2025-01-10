package com.swagLabs.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
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
public class CartPageTest extends BaseClass 
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
        super.tearDown(); // Call the tearDown method from BaseClass to handle cleanup
	}
	
	
	@Test(
		    priority = 1, 
		    description = "<html><body><b><pre><span style='color:yellow;'>"
		    		+ "This test verifies that user can remove mutiple items from the cart."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyUserCanRemoveItemsFromCart() throws InterruptedException, IOException 
	{
		LogUtils.info("********************************************************");
		LogUtils.info("***** <<<<< Starting verifyUserCanRemoveItemsFromCart >>>>> *****");
	
	    try 
	    {
	    	new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	        List<WebElement> products = new InventoryPageTest().addItemsToCart();

	        selUtils.clickOnMultipleWebElementsReverseOrder(cartPage.getRemoveButton());
	        selUtils.waitTime(5);
	        LogUtils.info("Removed all products from the cart.");
	        ReportUtils.addScreenshot("Removed all products from the cart.");
	        
	        String cartItemDetails = cartPage.getCartItemDetails().getText().toLowerCase();
	        
	        new LoginPageTest().logOut();
	        
	        for (WebElement product : products) 
	        {
	            Assert.assertFalse(cartItemDetails.contains(product.getText().toLowerCase()), "Added product should found in cart.");
	            LogUtils.info("CartPage has removed the item -" + product.getText().toLowerCase());
		        ReportUtils.logMessage("CartPage has removed the item -" + product.getText().toLowerCase());
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
	        LogUtils.info("***** Finished verifyUserCanRemoveItemsFromCart *****");
	        LogUtils.info("********************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }
		
	}
	
	
	
	@Test(
		    priority = 2, 
		    description = "<html><body><b><pre><span style='color:yellow;'>"
		    		+ "This test verifies that user can navigate to checkout page for futher process."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyUserCanCheckOut() throws InterruptedException, IOException 
	{
		LogUtils.info("********************************************************");
		LogUtils.info("***** <<<<< Starting verifyUserCanCheckOut >>>>> *****");
	
	    try {
	    	new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	    	new InventoryPageTest().addItemsToCart();
	    	checkout();
	        Assert.assertEquals(driver.getTitle(), "Swag Labs");
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
	        LogUtils.info("***** Finished verifyUserCanCheckOut *****");
	        LogUtils.info("********************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }
	    
	}
	
	
	
	
	public void checkout() throws InterruptedException 
    {
        selUtils.scrollToElement(cartPage.getCheckoutButton());
        selUtils.waitTime(3);
        LogUtils.info("Scrolled to Checkout button.");
        ReportUtils.addScreenshot("Scrolled to checkout button.");
        
        selUtils.waitAndClick(cartPage.getCheckoutButton(), 3);
        selUtils.waitTime(3);
        LogUtils.info("Checkout page opened.");
        ReportUtils.addScreenshot("Checkout page opened.");
    	
    }

	
	
	

}























