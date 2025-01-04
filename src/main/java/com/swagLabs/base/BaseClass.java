package com.swagLabs.base;

import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import com.swagLabs.pom.BasePage;
import com.swagLabs.pom.CartPage;
import com.swagLabs.pom.CheckoutCompletePage;
import com.swagLabs.pom.CheckoutOverviewPage;
import com.swagLabs.pom.CheckoutPage;
import com.swagLabs.pom.InventoryPage;
import com.swagLabs.pom.LoginPage;
import com.swagLabs.pom.ProductPage;
import com.swagLabs.utilities.ExtentReportManager;
import com.swagLabs.utilities.PropertiesReader;
import com.swagLabs.utilities.SeleniumUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains browser initialization and login and logout method 
 * and objects of POM pages.
 */
public class BaseClass {

	public static WebDriver driver;

	public static BasePage basePage;
	public static LoginPage loginPage;
	public static InventoryPage inventoryPage;
	public static ProductPage productPage;
	public static CartPage cartPage;
	public static CheckoutPage checkoutPage;
	public static CheckoutOverviewPage checkoutOverviewPage;
	public static CheckoutCompletePage checkoutCompletePage;

	public static ExtentReportManager extentReportManager;
	public static SeleniumUtils SelUtils;
		
	public static String browserName;
	public static String browserVersion;
	public static String platformName;
	
	@Parameters({ "browser" }) // This parameter value is passed from testng.xml that is browser name.
	public static void openBrowser(String browser) throws InterruptedException 
	{
		if (browser.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
		    options.addArguments("--force-device-scale-factor=" + (80 / 100.0));
		    options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
		} 
		else if (browser.equalsIgnoreCase("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
		    options.addArguments("--force-device-scale-factor=" + (80 / 100.0));
		    options.addArguments("start-maximized");
			driver = new EdgeDriver(options);
		}
		
		basePage = new BasePage(driver);
		loginPage = new LoginPage(driver);
		inventoryPage = new InventoryPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);
		checkoutOverviewPage = new CheckoutOverviewPage(driver);
		checkoutCompletePage = new CheckoutCompletePage(driver);

		extentReportManager = new ExtentReportManager();
		SelUtils = new SeleniumUtils();
		
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();

        // Retrieve browser details
        browserName = capabilities.getBrowserName();
        browserVersion = capabilities.getBrowserVersion();
        platformName = capabilities.getPlatformName().toString();
       
        System.out.println(browserName);
        System.out.println(browserVersion);
        System.out.println(platformName);

        
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(PropertiesReader.getProperty("baseUrl"));

		SelUtils.waitTime(1);
	}

	

}



