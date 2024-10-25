package testCase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.swagLabs.pojo.BaseClass;
import com.swagLabs.utilities.AutoConstant;

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class SwagLabs3ProductPageTest extends BaseClass {

	@Parameters("browser")
	@BeforeMethod()
	public void setUp(String browser) throws InterruptedException, IOException 
	{
		login(browser, AutoConstant.username, AutoConstant.password);
		BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		swagLabsHomepage.clickOnProduct();
		BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
	public void verifyUserCanAddProductToCart() throws InterruptedException, IOException 
	{
		swagLabsProductpage.clickOnAddToCartButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "clicked on 'Add To cart' button.");
		
		swagLabsProductpage.clickOnCartButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened cart page.");

		boolean check = BaseClass.driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).isDisplayed();
		Assert.assertEquals(true,check);
		Thread.sleep(100);
	}

	@Test(priority = 2)
	public void verifyUserCanRemoveProductFromCart() throws InterruptedException, IOException 
	{
		SoftAssert sf = new SoftAssert();
		swagLabsProductpage.clickOnAddToCartButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Added item to cart.");

		swagLabsProductpage.clickOnCartButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened cart page.");

		boolean check = BaseClass.driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).isDisplayed();
		sf.assertEquals(true,check, "Given item is not present in the cart.");
		
		BaseClass.driver.navigate().back();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened inventory page.");
		
		swagLabsHomepage.clickOnProduct();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened product page.");

		swagLabsProductpage.clickOnRemoveButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Clicked on remove button.");

		swagLabsProductpage.clickOnCartButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened cart page.");

		boolean check1 = BaseClass.driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).isDisplayed();
		sf.assertEquals(false,check1, "Item should not be present in cart.");
	}


}
