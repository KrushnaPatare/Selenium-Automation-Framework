package testCase;

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

import com.aventstack.extentreports.Status;
import com.swagLabs.pojo.BaseClass;
import com.swagLabs.utilities.DataProviders;
import com.swagLabs.utilities.ExtentReportManager;

@Listeners(com.swagLabs.utilities.ExtentReportManager.class)
public class SwagLabs1LoginPageTest extends BaseClass 
{   
	@Parameters({ "browser" })  
	@BeforeMethod()
	public void setUp(@Optional("chrome") String browser) throws IOException, InterruptedException 
	{	
			BaseClass.openBrowser(browser);
			BaseClass.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
			BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//	BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Opened login page.");
	}
	
	@AfterMethod
	public void closeBrowser(ITestResult result) throws InterruptedException 
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
	

	
	//This is data driven test case.
	@Test(priority = 1, dataProvider = "loginData", dataProviderClass = DataProviders.class)
	public void checkLoginFunctionality(String testcaseName, String username, String password,String result)
			throws InterruptedException, IOException 
	{
		Thread.sleep(2000);
		ExtentReportManager.test.log(Status.INFO,"<p><b>Parameters of testcase:<br>"
				+ "testcaseName = "+ testcaseName +"<br>"
				+ "username = "+ username +"<br>"
				+"password = "+ password +"<br>"
				+"result = "+result 
				+"</b>"+"</p>");

		swagLabsLoginPage.enterUsername(username);
		Thread.sleep(2000);
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Entering username.");

		
		swagLabsLoginPage.enterPassword(password);
		Thread.sleep(2000);
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Entering password.");

		swagLabsLoginPage.clickOnLogin();
		Thread.sleep(500);
		BaseClass.screenshot.takeScreenshotAndLog(BaseClass.driver, "Successful login.");

		BaseClass.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		String actualUrl = BaseClass.driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		
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
