package testCase;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swagLabs.pojo.BaseClass;
import com.swagLabs.utilities.AutoConstant;

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class SwagLabs7CheckoutCompletepageTest extends BaseClass {

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws EncryptedDocumentException, IOException, InterruptedException 
	{
		login(browser, AutoConstant.username,AutoConstant.password);
		BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		swagLabsHomepage.clickOnFirstAddToCartButton();
		
		
		swagLabsHomepage.clickOnCartButton();
		BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		swagLabsCartPage.clickOnCheckoutButton();
		BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		swagLabCheckoutStepOnePage.enterCheckoutDetails("Kris", "smith", "123456");
		BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		swagLabsCheckoutStepTwoPage.clickOnfinishButton();
	}

	
	@AfterMethod
	public void tearDown(ITestResult result) 
	{
		try 
		{
			BaseClass.logout();
			BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Lougout successfully.");
			Thread.sleep(1000);
			BaseClass.driver.quit();
		}

		catch (Exception e) 
		{
			e.printStackTrace();
			BaseClass.driver.quit();
		}	
	}

	
	@Test(priority = 1)
	public void verifyOrderConformationMessageDisplayed() throws InterruptedException 
	{
		String actualGreetText = swagLabsCheckoutComplete.getGreetInformation();
		String expectedGreetText = "Thank you for your order!\n"
				+ "Your order has been dispatched, and will arrive just as fast as the pony can get there!\n"
				+ "Back Home";
		Assert.assertEquals(expectedGreetText, actualGreetText, "Greet information did not match.");
		Thread.sleep(100);
	}

	
	@Test(priority = 2)
	public void openCheckoutComplete_BackToHomeButton_RedirectedToProductpage() throws InterruptedException, IOException 
	{
		swagLabsCheckoutComplete.clickOnBackToHomeButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened inventory page.");

		String actualUrl = BaseClass.driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory.html";

		Assert.assertEquals(expectedUrl, actualUrl, "Url did not match.");
		Thread.sleep(100);
	}

}
