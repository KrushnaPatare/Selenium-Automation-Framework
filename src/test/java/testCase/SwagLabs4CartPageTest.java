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

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class SwagLabs4CartPageTest extends BaseClass {

	@Parameters("browser")
	@BeforeMethod()
	public void Setup(@Optional("chrome")String browser) throws InterruptedException, IOException
	{
		login(browser, AutoConstant.username,AutoConstant.password);

		BaseClass.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		swagLabsHomepage.clickOnCartButton();

		Thread.sleep(2000);
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
		}	}

	
	@Test(priority = 1)
	public void verifyNavigationToCheckoutPage() throws InterruptedException, IOException 
	{
		swagLabsCartPage.clickOnCheckoutButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened checkout page.");
		Thread.sleep(2000);
		
		String actualUrl = BaseClass.driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
		Assert.assertEquals(expectedUrl, actualUrl, "The page title are not matching");
		Thread.sleep(100);
	}

	
	@Test(priority = 2)
	public void navigateToHomepageAndAddItems() throws InterruptedException, IOException 
	{
		swagLabsCartPage.clickOncontinueShopping();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened inventory page.");

		BaseClass.driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		swagLabsHomepage.clickOnCartButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Item added to cart.");

		Assert.assertTrue(BaseClass.driver.findElement(By.xpath("//div[@class='inventory_item_name']")).isDisplayed(), "Actual added item is not displayed in the cart.");
		Thread.sleep(100);
	}
	
	

	
}
