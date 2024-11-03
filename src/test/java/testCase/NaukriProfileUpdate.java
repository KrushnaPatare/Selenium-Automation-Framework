package testCase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NaukriProfileUpdate 
{
	@Test()
	public static void updateNaukriProfile() throws InterruptedException, IOException 
	{
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.get("https://www.naukri.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		Actions actions = new Actions(driver);
//	    actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).pause(2000).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).build().perform();
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='80%'");
		Thread.sleep(10000);
		
		WebElement loginPageButton = driver.findElement(By.xpath("//a[@title='Jobseeker Login']"));
		loginPageButton.click();
		Thread.sleep(6000);
		
		
		WebElement username = driver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']"));
		username.sendKeys("krushnapatare001@gmail.com");
		Thread.sleep(10000);

		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("9766827220");
		Thread.sleep(9000);
		
		WebElement loginButton = driver.findElement(By.xpath("//button[@class='btn-primary loginButton']"));
		loginButton.click();
		Thread.sleep(10000);
		
		WebElement viewProfileButton = driver.findElement(By.xpath("//a[@href='/mnjuser/profile']"));
		js.executeScript("document.body.style.zoom='80%'");
		viewProfileButton.click();
		Thread.sleep(10000);

		WebElement deleteIcon = driver.findElement(By.xpath("//i[@data-title='delete-resume']"));
		deleteIcon.click();
		Thread.sleep(3000);
		
		WebElement deleteButton = driver.findElement(By.xpath("(//button[text()='Delete'])[2]"));
		deleteButton.click();
		Thread.sleep(7000);

		WebElement uploadButton = driver.findElement(By.xpath("//span[@class='dummyUploadNewCTA']"));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", uploadButton);
		Thread.sleep(2000);
		uploadButton.click();
		Thread.sleep(7000);
		
		Runtime.getRuntime().exec("F:\\WorkSpace\\SwagLabs-Ecommerce\\DDT\\FileUpload.exe");
		Thread.sleep(7000);
		
		WebElement basicDetailsButton = driver.findElement(By.xpath("//em[text()='editOneTheme']"));
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000);
		basicDetailsButton.click();
		Thread.sleep(3000);
		
		WebElement saveDetailsButton = driver.findElement(By.xpath("//button[@id='saveBasicDetailsBtn']"));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", saveDetailsButton);
		saveDetailsButton.click();
		Thread.sleep(10000);
		
		driver.quit();
	}

}
