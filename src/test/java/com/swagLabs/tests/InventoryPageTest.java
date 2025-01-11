package com.swagLabs.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swagLabs.base.BaseClass;
import com.swagLabs.utilities.ExceptionHandler;
import com.swagLabs.utilities.ExtentReportManager;
import com.swagLabs.utilities.LogUtils;
import com.swagLabs.utilities.PropertiesReader;
import com.swagLabs.utilities.ReportUtils;
import com.swagLabs.utilities.Validator;

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class InventoryPageTest extends BaseClass 
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
		    		+ "This test verifies that user is able to different social media handles of SwagLabs.com."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyUserIsAbleToNavigateToSocialMediaSites() throws InterruptedException, IOException 
	{
	    LogUtils.info("*****************************************************************************");
	    LogUtils.info("***** <<<<< Starting verifyUserIsAbleToNavigateToSocialMediaSites >>>>> *****");

	    try 
	    {
	        new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	        LogUtils.info("Login successful.");
	        ReportUtils.addScreenshot("Login successful.");
	        
	        LogUtils.info("Navigating to LinkedIn page.");
	        selUtils.scrollToElement(basePage.getLinkedInPageButton());
	        selUtils.waitTime(3);
	        ReportUtils.addScreenshot("Scrolled to LinkeInPageButton.");
	        selUtils.waitAndClick(basePage.getLinkedInPageButton(), 2);
	        selUtils.waitTime(5);
	        ReportUtils.addScreenshot("Opened LinkeIn Page.");
	        selUtils.switchToWindowByTitle("Sauce Labs | LinkedIn");
	        selUtils.waitTime(10);
	        System.out.println(driver.getTitle());

	        Validator.verifyTitle(driver.getTitle(), "Sauce Labs | LinkedIn");
	        
	        LogUtils.info("LinkedIn page title validation passed.");

	        selUtils.switchToWindowByTitle("Swag Labs");
	        ReportUtils.addScreenshot("Navigated to HomePage.");

	        selUtils.closeOtherWindows();

	        LogUtils.info("Navigating to Twitter page.");
	        selUtils.scrollToElement(basePage.getTwitterPageButton());
	        selUtils.waitTime(3);
	        ReportUtils.addScreenshot("Scrolled to TwitterPageButton.");

	        selUtils.waitAndClick(basePage.getTwitterPageButton(), 5);
	        selUtils.switchToWindowByTitle("Sauce Labs (@saucelabs) / X");
	        selUtils.waitTime(5);
	        ReportUtils.addScreenshot("Opened Twitter Page.");

	        Validator.verifyTitle(driver.getTitle(), "Sauce Labs (@saucelabs) / X");

	        LogUtils.info("Twitter page title validation passed.");

	        selUtils.switchToWindowByTitle("Swag Labs");
	        ReportUtils.addScreenshot("Navigated to HomePage.");
	       
	        selUtils.closeOtherWindows();

	        LogUtils.info("Navigating to Facebook page.");
	        selUtils.scrollToElement(basePage.getFacebookPageButton());
	        ReportUtils.addScreenshot("Scrolled to FacebookPageButton.");
	        selUtils.waitAndClick(basePage.getFacebookPageButton(), 5);
	        selUtils.waitTime(5);
	        ReportUtils.addScreenshot("Opened Facebook Page.");

	        selUtils.switchToWindowByTitle("Sauce Labs | Facebook");
	        
	        Validator.verifyTitle(driver.getTitle(), "Sauce Labs | Facebook");
	        
	        LogUtils.info("Facebook page title validation passed.");

	        selUtils.switchToWindowByTitle("Swag Labs");
	        ReportUtils.addScreenshot("Navigated to HomePage.");
	        selUtils.closeOtherWindows();
	        
	        new LoginPageTest().logOut();
	    } 
	    catch (AssertionError e) 
	    {
			ExceptionHandler.handleException(e, "Assertion failure during test execution.");
	    } 
	    catch (Exception e) 
	    {
			ExceptionHandler.handleException(e, "Assertion failure during test execution.");
	    } 
	    finally 
	    {
	        LogUtils.info("***** Finished verifyUserIsAbleToNavigateToSocialMediaSites *****");
	        LogUtils.info("*****************************************************************");
	        ExtentReportManager.getTest().info("Test finished.");
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

	    try 
	    {	 
	        new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	        List<WebElement> products = addItemsToCart();
	        
	        String cartItemDetails = cartPage.getCartItemDetails().getText().toLowerCase();
	        
	        for (WebElement product : products) 
	        {
	        	Validator.verifyTrue(cartItemDetails.contains(product.getText().toLowerCase()), "CartPage has added item -" + product.getText().toLowerCase());
	        }
	    }
	    catch (AssertionError e) 
	    {
			ExceptionHandler.handleException(e, "Assertion failure during test execution.");
	    } 
	    catch (Exception e) 
	    {
			ExceptionHandler.handleException(e, "Assertion failure during test execution.");
	    } 
	    finally 
	    {
	        LogUtils.info("***** Finished verifyUserCanAddItemToCart *****");
	        LogUtils.info("***********************************************");
	        ExtentReportManager.getTest().info("Test finished.");
	    }	    
	
	}
	
	
	public List<WebElement> addItemsToCart() throws InterruptedException 
	{
        List<WebElement> products = inventoryPage.getProductNames();

    	selUtils.clickOnMultipleWebElementsReverseOrder(inventoryPage.getAddToCartButton());
    	selUtils.waitTime(5);
        LogUtils.info("Added all products to the cart.");
        ReportUtils.addScreenshot("Added all products to the cart.");

    	selUtils.waitAndClick(basePage.getShoppingCartIcon(), 2);
    	selUtils.waitTime(3);
		
    	LogUtils.info("Opened open cart page.");
        ReportUtils.addScreenshot("Opened open cart page.");
        return products;
	}
	
	

}



















