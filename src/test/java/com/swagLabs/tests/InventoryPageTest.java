package com.swagLabs.tests;

import java.io.IOException;
import java.time.Duration;
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
public class InventoryPageTest extends BaseClass 
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
		    		+ "This test verifies that user is able to different social media handles of SwagLabs.com."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyUserIsAbleToNavigateToSocialMediaSites() throws InterruptedException, IOException 
	{
	    LogUtils.info("********************************************************");
	    LogUtils.info("***** <<<<< Starting verifyUserIsAbleToNavigateToSocialMediaSites >>>>> *****");

	    try {
	        new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	        LogUtils.info("Login successful.");
	        ReportUtils.addScreenshot("Login successful.");
	        
	        LogUtils.info("Navigating to LinkedIn page.");
	        SelUtils.scrollToElement(basePage.getLinkedInPageButton(), driver);
	        SelUtils.waitTime(3);
	        ReportUtils.addScreenshot("Scrolled to LinkeInPageButton.");
	        SelUtils.waitAndClick(basePage.getLinkedInPageButton(), 2);
	        SelUtils.waitTime(5);
	        ReportUtils.addScreenshot("Opened LinkeIn Page.");
	        SelUtils.switchToWindowByTitle("Sauce Labs | LinkedIn");
	        SelUtils.waitTime(10);
	        System.out.println(driver.getTitle());

	        Assert.assertEquals( driver.getTitle(), "Sauce Labs | LinkedIn", "Page title should match.");
	        LogUtils.info("LinkedIn page title validation passed.");

	        SelUtils.switchToWindowByTitle("Swag Labs");
	        ReportUtils.addScreenshot("Navigated to HomePage.");

	        SelUtils.closeOtherWindows();

	        LogUtils.info("Navigating to Twitter page.");
	        SelUtils.scrollToElement(basePage.getTwitterPageButton(), driver);
	        SelUtils.waitTime(3);
	        ReportUtils.addScreenshot("Scrolled to TwitterPageButton.");

	        SelUtils.waitAndClick(basePage.getTwitterPageButton(), 5);
	        SelUtils.switchToWindowByTitle("Sauce Labs (@saucelabs) / X");
	        SelUtils.waitTime(5);
	        ReportUtils.addScreenshot("Opened Twitter Page.");

	        Assert.assertEquals(driver.getTitle(), "Sauce Labs (@saucelabs) / X", "Page title should match.");
	        LogUtils.info("Twitter page title validation passed.");

	        SelUtils.switchToWindowByTitle("Swag Labs");
	        ReportUtils.addScreenshot("Navigated to HomePage.");
	       
	        SelUtils.closeOtherWindows();

	        LogUtils.info("Navigating to Facebook page.");
	        SelUtils.scrollToElement(basePage.getFacebookPageButton(), driver);
	        ReportUtils.addScreenshot("Scrolled to FacebookPageButton.");
	        SelUtils.waitAndClick(basePage.getFacebookPageButton(), 5);
	        SelUtils.waitTime(5);
	        ReportUtils.addScreenshot("Opened Facebook Page.");

	        SelUtils.switchToWindowByTitle("Sauce Labs | San Francisco CA | Facebook");
	        Assert.assertEquals(driver.getTitle(), "Sauce Labs | San Francisco CA | Facebook", "Page title should match.");
	        LogUtils.info("Facebook page title validation passed.");

	        SelUtils.switchToWindowByTitle("Swag Labs");
	        ReportUtils.addScreenshot("Navigated to HomePage.");
	        SelUtils.closeOtherWindows();
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
	        LogUtils.info("***** Finished verifyUserIsAbleToNavigateToSocialMediaSites *****");
	        LogUtils.info("********************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }
	}

	
	

	@Test(
		    priority = 2, 
		    description = "<html><body><b><pre><span style='color:yellow;'>"
		    		+ "This test verifies that user can add items to cart he wants to buy."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyUserCanAddItemToCart() throws InterruptedException, IOException 
	{
		LogUtils.info("********************************************************************************");
		LogUtils.info("***** <<<<< Starting verifyUserCanAddItemToCart >>>>> *****");

	    try {	        
	        List<WebElement> products = addItemsToCart();
	        
	        String cartItemDetails = cartPage.getCartItemDetails().getText().toLowerCase();
	        
	        for (WebElement product : products) 
	        {
	            Assert.assertTrue(cartItemDetails.contains(product.getText().toLowerCase()), "Added product should found in cart.");
	            LogUtils.info("CartPage has added item -" + product.getText().toLowerCase());
		        ReportUtils.logMessage("CartPage has added item -" + product.getText().toLowerCase());
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
	        LogUtils.info("***** Finished verifyUserCanAddItemToCart *****");
	        LogUtils.info("********************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }	    
	
	}
	
	
	public List<WebElement> addItemsToCart() throws InterruptedException 
	{
        new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
        LogUtils.info("Login successful.");
        
        List<WebElement> products = inventoryPage.getProductNames();

    	SelUtils.clickOnMultipleWebElementsReverseOrder(inventoryPage.getAddToCartButton(), driver);
    	SelUtils.waitTime(5);
        LogUtils.info("Added all products to the cart.");
        ReportUtils.addScreenshot("Added all products to the cart.");

    	SelUtils.waitAndClick(basePage.getShoppingCartIcon(), 2);
    	SelUtils.waitTime(3);
		
    	LogUtils.info("Opened open cart page.");
        ReportUtils.addScreenshot("Opened open cart page.");
        return products;
	}
	
	

}



















