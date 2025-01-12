package com.swagLabs.base;


import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

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
 * This class contains browser initialization and login and logout methods 
 * and objects of POM pages.
 */
public class BaseClass 
{
	public static WebDriver driver = null;
	
    public static BasePage basePage;
    public static LoginPage loginPage;
    public static InventoryPage inventoryPage;
    public static ProductPage productPage;
    public static CartPage cartPage;
    public static CheckoutPage checkoutPage;
    public static CheckoutOverviewPage checkoutOverviewPage;
    public static CheckoutCompletePage checkoutCompletePage;

    public static ExtentReportManager extentReportManager;
    public static SeleniumUtils selUtils;

    public static String browserName;
    public static String browserVersion;
    public static String platformName;

    public void openBrowser(String browser) throws InterruptedException 
    {   
        if (browser.equalsIgnoreCase("chrome")) 
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            
            // Uncomment below lines to enable headless mode and related options:
            
            /* 
            options.addArguments("--headless"); // Enable headless mode
            options.addArguments("--disable-gpu"); // Disables GPU hardware acceleration (optional)
            options.addArguments("--window-size=1920,1080"); // Set window size (important for headless)
            options.addArguments("--disable-extensions"); // Disable extensions
            options.addArguments("--no-sandbox"); // Required for running as root (Linux-specific)
            */
            
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
        else 
        {
        	System.out.println("Wrong browser name is entered.");
        }
        
        
        // Initialize Page Objects
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);

        // Initialize utility classes
        extentReportManager = new ExtentReportManager();
        selUtils = new SeleniumUtils(driver);
        
        // Retrieve browser capabilities and details
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        browserName = capabilities.getBrowserName();
        browserVersion = capabilities.getBrowserVersion();
        platformName = capabilities.getPlatformName().toString();

        // Log browser details for debugging
        System.out.println(browserName);
        System.out.println(browserVersion);
        System.out.println(platformName);

        // Set timeouts
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        driver.manage().deleteAllCookies();  // Clears all cookies

        // Open the base URL
        driver.get(PropertiesReader.getProperty("baseUrl"));

        // Wait time for UI elements to load
        selUtils.waitTime(2);
    }

    public void tearDown() 
    {
        if (driver != null) 
        {
            driver.quit();
        }
    }
}




























