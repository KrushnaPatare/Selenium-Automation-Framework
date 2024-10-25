package com.swagLabs.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.swagLabs.pojo.BaseClass;

public class SwagLabsLoginPage {

	WebDriver driver = BaseClass.driver;
	@FindBy(xpath = "//div[@class='login_logo']")
	private WebElement logo;
	@FindBy(xpath = "//input[@id='user-name']")
	private WebElement username;
	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginButton;
	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement errorBox;
	@FindBy(xpath = "//button[@class='error-button']")
	private WebElement errorCancelButton;
	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement usernamePlaceHolder;
	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passwordPlaceHolder;

	public SwagLabsLoginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String USERNAME) 
	{
		username.sendKeys(USERNAME);
	}

	public void enterPassword(String PASSWORD) 
	{
		password.sendKeys(PASSWORD);
	}

	public void clickOnLogin() 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginButton);
	}
}
