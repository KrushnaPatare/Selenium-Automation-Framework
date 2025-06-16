package com.swagLabs.tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swagLabs.base.BaseClass;
import com.swagLabs.utilities.DataProviders;
import com.swagLabs.utilities.ExceptionHandler;
import com.swagLabs.utilities.ExtentReportManager;
import com.swagLabs.utilities.LogUtils;
import com.swagLabs.utilities.PropertiesReader;
import com.swagLabs.utilities.ReportUtils;
import com.swagLabs.utilities.Validator;

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
	public void tearDown() 
	{	
		super.tearDown();
	}
	
	
	@Test(
		    priority = 1, 
    		dataProvider = "checkoutData", 
		    dataProviderClass = DataProviders.class,
		    description = "<html><body><b><pre><span style='color:yellow;'>"
			    		+ "This test verifies that user can complete the order placing process."
			    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyCheckoutFunctionality
	(
		 String firstname, 
		 String lastname, 
		 String postalCode, 
		 String result
	) throws InterruptedException, IOException 
	{
		LogUtils.info("********************************************************");
		LogUtils.info("***** <<<<< Starting verifyCheckoutFunctionality >>>>> *****");
	
	    try 
	    {
	    	new LoginPageTest().logIn(PropertiesReader.getPropertyFromFile("username"), PropertiesReader.getPropertyFromFile("password"));
	        Validator.verifyTrue(basePage.getShoppingCartIcon().isDisplayed(), "LogIn validation successfull !!!");

	    	new InventoryPageTest().addItemsToCart();
	        Validator.verifyTrue(cartPage.getCheckoutButton().isDisplayed(), "Item addition to cart validation successfull !!!");

	    	new CartPageTest().checkout();
	        Validator.verifyTrue(checkoutPage.getFirstNameField().isDisplayed(), "Navigated to checkout page validation successfull !!!");

	    	checkOutComplete(firstname, lastname, postalCode);
	        Validator.verifyUrl(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html", result);

			new LoginPageTest().logOut();
	        Validator.verifyTrue(loginPage.getLoginButton().isDisplayed(), "Logout validation successful !!!");
	    } 
	    catch (AssertionError e) 
	    {
			ExceptionHandler.handleAssertionError(e, "Assertion failure during test execution.");
	    } 
	    catch (Exception e) 
	    {
			ExceptionHandler.handleException(e, "Assertion failure during test execution.");
	    } 
	    finally 
	    {
	        LogUtils.info("***** Finished verifyCheckoutFunctionality *****");
	        LogUtils.info("************************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }
		
	}
	
	

	
	
	public void checkOutComplete(String firstname, String lastname, String postalCode) throws InterruptedException 
	{
        selUtils.sendKeysMethod(checkoutPage.getFirstNameField(), firstname, 2);
        selUtils.sendKeysMethod(checkoutPage.getLastNameField(), lastname, 2);
        selUtils.sendKeysMethod(checkoutPage.getPostalCodeField(), postalCode,2);

        selUtils.waitForSeconds(3);
        
        LogUtils.info("Entered firstname, lastname, postalCode.");
        ReportUtils.addScreenshot("Entered firstname, lastname, postalCode.");
        
        selUtils.waitForSeconds(3);
        
        selUtils.waitForElementAndClick(checkoutPage.getContinueButton(), 3);
        LogUtils.info("Opened checkout complete page.");
        ReportUtils.addScreenshot("Opened checkout complete page.");
	}
}
