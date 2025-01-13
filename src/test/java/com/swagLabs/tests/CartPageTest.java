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
		LogUtils.info("*****************************************************************");
		LogUtils.info("***** <<<<< Starting verifyUserCanRemoveItemsFromCart >>>>> *****");
	
	    try 
	    {
	    	new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	        Validator.verifyTrue(basePage.getShoppingCartIcon().isDisplayed(), "LogIn validation successfull !!!");

	    	List<WebElement> products = new InventoryPageTest().addItemsToCart();
	        Validator.verifyTrue(cartPage.getCheckoutButton().isDisplayed(), "Item addition to cart validation successfull !!!");

	        selUtils.clickOnMultipleWebElementsReverseOrder(cartPage.getRemoveButton());
	        selUtils.waitTime(5);
	        LogUtils.info("Removed all products from the cart.");
	        ReportUtils.addScreenshot("Removed all products from the cart.");
	        
	        String cartItemDetails = cartPage.getCartItemDetails().getText().toLowerCase();
	        
	        new LoginPageTest().logOut();
	        Validator.verifyTrue(loginPage.getLoginButton().isDisplayed(), "Logout validation successful !!!");

	        for (WebElement product : products) 
	        {
	        	Validator.verifyTrue(cartItemDetails.contains(product.getText().toLowerCase()), "CartPage has removed the item -" + product.getText().toLowerCase());
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
	        LogUtils.info("***** Finished verifyUserCanRemoveItemsFromCart *****");
	        LogUtils.info("*****************************************************");
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
	
	    try 
	    {
	    	new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	        Validator.verifyTrue(basePage.getShoppingCartIcon().isDisplayed(), "LogIn validation successfull !!!");
	        
	        new InventoryPageTest().addItemsToCart();
	        Validator.verifyTrue(cartPage.getCheckoutButton().isDisplayed(), "Item addition to cart validation successfull !!!");

	    	checkout();
	        Validator.verifyUrl(driver.getTitle(), "Swag Labs");
	        
	        new LoginPageTest().logOut();
	        Validator.verifyTrue(loginPage.getLoginButton().isDisplayed(), "Logout validation successful !!!");
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
	        LogUtils.info("****** Finished verifyUserCanCheckOut ******");
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























