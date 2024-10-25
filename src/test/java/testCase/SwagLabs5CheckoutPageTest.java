package testCase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swagLabs.pojo.BaseClass;
import com.swagLabs.utilities.AutoConstant;
import com.swagLabs.utilities.DataProviders;

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class SwagLabs5CheckoutPageTest extends BaseClass {

	@Parameters("browser")
	@BeforeMethod
	public void Setup(@Optional("chrome")String browser) throws InterruptedException, IOException 
	{
		login(browser, AutoConstant.username,AutoConstant.password);
		BaseClass.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		BaseClass.driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		
		swagLabsHomepage.clickOnCartButton();

		swagLabsCartPage.clickOnCheckoutButton();

		
	}

	@AfterMethod
	public void closeBrowser(ITestResult result) 
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
	public void verifyCancelCheckoutFunctionality() throws InterruptedException, IOException 
	{
		swagLabCheckoutStepOnePage.clickCancelButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened Cart page.");

		String actualUrl = BaseClass.driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/cart.html";

		Assert.assertEquals(actualUrl, expectedUrl, "Page url didnot match maybe opened different page.");
	}
	
	
	@Test(priority = 2, dataProvider = "checkoutData", dataProviderClass = DataProviders.class)
	public void verifyCheckoutFunctionality(String firstname, String lastname, String pincode, String result) throws InterruptedException, IOException 
	{
		swagLabCheckoutStepOnePage.enterFirstname(firstname);
		swagLabCheckoutStepOnePage.enterLastname(lastname);
		swagLabCheckoutStepOnePage.enterPincode(pincode);
		swagLabCheckoutStepOnePage.clickContinueButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Entered user details like firstname, lastname & pincode.");
		
		String actualUrl = BaseClass.driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
		
		if(result.equalsIgnoreCase("pass")) 
		{
			Assert.assertEquals(actualUrl, expectedUrl, "Url should match.");
		}
		else if(result.equalsIgnoreCase("fail")) 
		{
			Assert.assertNotEquals(actualUrl, expectedUrl, "Url should not match.");
		}
	}

}
