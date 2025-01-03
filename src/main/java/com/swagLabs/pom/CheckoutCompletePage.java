package com.swagLabs.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage 
{	 
    @FindBy(xpath = "//div[@id='checkout_complete_container']")
    private WebElement checkoutCompleteContainer;
    
    @FindBy(xpath = "//button[text()='Back Home']")
    private WebElement backHomeButton;
    
    
    public CheckoutCompletePage(WebDriver driver) 
    {
        PageFactory.initElements(driver, this);
    }


    public WebElement getCheckoutCompleteContainer() {return checkoutCompleteContainer;}

    public WebElement getBackHomeButton() {return backHomeButton;}


}



















	
	
	
