package testCase;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.swagLabs.pojo.BaseClass;
import com.swagLabs.utilities.AutoConstant;

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class SwagLabs2InventoryPageTest extends BaseClass 
{

	@Parameters("browser")
	@BeforeMethod()
	public void Setup(@Optional("chrome")String browser) throws InterruptedException, IOException 
	{
		BaseClass.login(browser, AutoConstant.username,AutoConstant.password);
		BaseClass.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
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
	public void verifySortFunctionality() throws InterruptedException, IOException 
	{
		boolean checkAscendingOrder = BaseClass.swagLabsHomepage.sortInAscendingOrder();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Sorted items in ascending order.");

		boolean checkDescendingOrder = BaseClass.swagLabsHomepage.sortInDescendingOrder();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Sorted items in descending order.");

		boolean checkLowToHigh = BaseClass.swagLabsHomepage.sortByLowToHighPrice();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Sorted item from low price to high price.");

		boolean checkHighToLow = BaseClass.swagLabsHomepage.sortByHighToLowPrice();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Sorted item from high price to low price.");

		SoftAssert sf = new SoftAssert();
		sf.assertTrue(checkAscendingOrder, "Items did not sort in ascending order.");
		sf.assertTrue(checkDescendingOrder, "Items did not sort in descendin order.");
		sf.assertTrue(checkLowToHigh, "Items did not sort from low price to high price.");
		sf.assertTrue(checkHighToLow, "Items did not sort from high price to low price.");
		sf.assertAll();
	}
	
	
	@Test(priority = 2)
	public void verifyUserIsAbleToNavigateToSocialMediaSites() throws InterruptedException, IOException 
	{
		String actualTwitterPageTitle = BaseClass.swagLabsHomepage.navigateToTwitterPage();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Successfully navigated to Twitter page.");

		String actualFacebookPageTitle = BaseClass.swagLabsHomepage.navigateToFacebookPage();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Successfully navigated to Facebook page.");

		String actualLinkedInPageTitle = BaseClass.swagLabsHomepage.navigateToLinkedInPage();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Successfully navigated to LinkedIn page.");

		String expectedTwitterPageTitle = "Sauce Labs (@saucelabs) / X";
		String expectedFacebookPageTitle = "Sauce Labs | San Francisco CA | Facebook";
		String expectedLinkedInPageTitle = "Sauce Labs | LinkedIn";
		SoftAssert sf = new SoftAssert();
		
		sf.assertEquals(actualTwitterPageTitle,expectedTwitterPageTitle,"Page tiele didnot match.");
		sf.assertEquals(actualFacebookPageTitle,expectedFacebookPageTitle, "Page tiele didnot match.");
		sf.assertEquals(actualLinkedInPageTitle,expectedLinkedInPageTitle, "Page tiele didnot match.");
	}
	
	
	@Test(priority = 3)
	public void verifyUserCanAddItemToCart() throws InterruptedException, AWTException, IOException 
	{
		String[] itemNames1 = BaseClass.swagLabsHomepage.addAllProductsToCart();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Successfully navigated to LinkedIn page.");
		
		BaseClass.swagLabsHomepage.clickOnCartButton();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Successfully navigated to LinkedIn page.");
		
		String[] itemNames2 = BaseClass.swagLabsCartPage.nameOfAddedElementsInCart();
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Successfully navigated to LinkedIn page.");
		
		if(itemNames1.length==itemNames2.length) 
		{
			for(int i=0;i<itemNames1.length;i++) 
			{
				boolean check = false;
				for(int b=0;b<itemNames2.length;b++) 
				{
					if(check = itemNames1[i].equals(itemNames2[b])) 
					{
						check=true;
					}
				}
				Assert.assertTrue(check, "Products names in cart did not match with that are added in it.");
			}
		}
		else {
				Assert.assertTrue(false,"Both array length does not matches.");
			 }
	}

}
