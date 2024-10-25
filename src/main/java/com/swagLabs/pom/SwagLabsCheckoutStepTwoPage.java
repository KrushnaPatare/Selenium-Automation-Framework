package com.swagLabs.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsCheckoutStepTwoPage {
	
	@FindBy (xpath = "//button[@name='cancel']") private WebElement cancelButton;
	@FindBy (xpath = "//button[@id='finish']") private WebElement finishButton;
	@FindBy (xpath = "//div[@class='checkout_summary_container']") private WebElement checkoutSummary;

	
	public SwagLabsCheckoutStepTwoPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public String getProductSummary() {
		
		String text = checkoutSummary.getText();
		return text;
	}
	
	public void clickOnCancelButton() {
		
		cancelButton.click();
	}
	
	public void clickOnfinishButton() {
		
		finishButton.click();
	}

}
