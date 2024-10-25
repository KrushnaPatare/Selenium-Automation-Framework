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
public class SwagLabs6CheckoutOverviewPageTest extends BaseClass {

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws EncryptedDocumentException, InterruptedException, IOException 
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
		}	}

	@Test(priority = 1)
	public void verifyUserCanOrderSuccessfully() throws InterruptedException, IOException 
	{
		swagLabsCheckoutStepTwoPage.clickOnfinishButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened inventory page.");

		String actualUrl = BaseClass.driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
		Assert.assertEquals(expectedUrl, actualUrl, "Url should not match.");
	}
	

	@Test(priority = 2)
	public void verifyCheckoutProcessCancellation() throws InterruptedException, IOException 
	{
		swagLabsCheckoutStepTwoPage.clickOnCancelButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened inventory page.");

		String actualUrl = BaseClass.driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(expectedUrl, actualUrl, "Url did not match.");
	}

	

}
