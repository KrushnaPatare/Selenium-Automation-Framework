package com.swagLabs.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsCheckoutComplete {

	
	@FindBy (xpath = "//button[@id='back-to-products']") private WebElement backToHomeButton;
	@FindBy (xpath = "//div[@class='checkout_complete_container']") private WebElement greetInformation;
	
	
	public SwagLabsCheckoutComplete(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String getGreetInformation() {
		
		String greetText =greetInformation.getText();
		return greetText;
	}
	
	
	public void clickOnBackToHomeButton() {
		
		backToHomeButton.click();
	}
	
	
	
	
	
}
