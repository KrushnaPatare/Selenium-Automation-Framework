package com.swagLabs.tests;

import java.io.IOException;

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
import com.swagLabs.utilities.Validator;

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class CheckoutCompletepageTest extends BaseClass 
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
		    		+ "This test verifies that user after order placing process completion, a success message is displayed."
		    		+ "</span></pre></b></body></html>"
		 )	
	public void verifyCheckoutSuccessMessage() throws InterruptedException, IOException 
	{
    	
		LogUtils.info("*************************************************************");
		LogUtils.info("***** <<<<< Starting verifyCheckoutSuccessMessage >>>>> *****");
	
	    try 
	    {
	    	new LoginPageTest().logIn(PropertiesReader.getProperty("username"), PropertiesReader.getProperty("password"));
	        Validator.verifyTrue(basePage.getShoppingCartIcon().isDisplayed(), "LogIn validation successfull !!!");

	    	new InventoryPageTest().addItemsToCart();
	        Validator.verifyTrue(cartPage.getCheckoutButton().isDisplayed(), "Item addition to cart validation successfull !!!");

	    	new CartPageTest().checkout();
	        Validator.verifyTrue(checkoutPage.getFirstNameField().isDisplayed(), "Navigated to checkout page validation successfull !!!");

			new CheckoutPageTest().checkOutComplete("Krushna", "Patare", "411016");
	        Validator.verifyTrue(checkoutOverviewPage.getFinishButton().isDisplayed(), "Checkout completed with user details validation successfull !!!");

	    	new CheckoutOverviewPageTest().completeCheckoutOverview();
	        Validator.verifyTrue(checkoutCompletePage.getBackHomeButton().isDisplayed(), "Order placed conformly !!!");
	    	
	    	String successMessage = "Thank you for your order!";
	    	String message = checkoutCompletePage.getCheckoutCompleteContainer().getText();
	        Validator.verifyTrue(message.toLowerCase().contains(successMessage.toLowerCase()), "Success message is displayed on page.");

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
	        LogUtils.info("***** Finished verifyCheckoutSuccessMessage *****");
	        LogUtils.info("*************************************************");
	        ExtentReportManager.test.info("Test finished.");
	    }
	}


}
